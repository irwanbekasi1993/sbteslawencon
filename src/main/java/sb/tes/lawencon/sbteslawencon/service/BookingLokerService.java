package sb.tes.lawencon.sbteslawencon.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sb.tes.lawencon.sbteslawencon.model.BookingLoker;
import sb.tes.lawencon.sbteslawencon.model.Loker;
import sb.tes.lawencon.sbteslawencon.model.Pembayaran;
import sb.tes.lawencon.sbteslawencon.model.User;
import sb.tes.lawencon.sbteslawencon.payload.request.UserBookingRequest;
import sb.tes.lawencon.sbteslawencon.payload.response.MessageResponse;
import sb.tes.lawencon.sbteslawencon.payload.response.UserBookingResponse;
import sb.tes.lawencon.sbteslawencon.repository.BookingLokerRepository;

@Service
public class BookingLokerService {

    @Autowired
    private BookingLokerRepository bookingLokerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LokerService lokerService;

    @Autowired
    private PembayaranService pembayaranService;

    public MessageResponse insertValidation(UserBookingRequest userBookingRequest){

        User u = new User();
        u.setNik(userBookingRequest.getNik());
        u.setNama(userBookingRequest.getNama());
        u.setNoHp(userBookingRequest.getNoHp());
        u.setEmail(userBookingRequest.getEmail());
        userService.insertValidation(u);        

        Loker l = new Loker();
        l.setNomorLoker(userBookingRequest.getNomorLoker());
        lokerService.insertValidation(l);

        Pembayaran p = new Pembayaran();
        p.setKodePembayaran("PAYMENT"+pembayaranService.countPembayaran());
        p.setJenisPembayaran(userBookingRequest.getJenisPembayaran());
        p.setJumlahPembayaran(userBookingRequest.getJumlahPembayaran());
        pembayaranService.insertValidation(p);

        BookingLoker bookingLoker = new BookingLoker();

        if(getBookingSaving(u.getNik(),l.getNomorLoker())!=null && getBookingReturning(u.getNik(), l.getNomorLoker())!=null){
            return new MessageResponse("data booking loker masih ada");
        }
        
        bookingLoker.setUser(u);
        bookingLoker.setLoker(l);
        bookingLoker.setPembayaran(p);
        Date dt = new Date();
        bookingLoker.setStartDate(new Timestamp(dt.getTime()));
        bookingLoker.setStatusBooking("not booked");
        bookingLokerRepository.save(bookingLoker);

        UserBookingResponse userBookingResponse = new UserBookingResponse();
        userBookingResponse.setNik(u.getNik());
        userBookingResponse.setNama(u.getNama());
        userBookingResponse.setNoHp(u.getNoHp());
        userBookingResponse.setEmail(u.getEmail());
        userBookingResponse.setNomorLoker(l.getNomorLoker());
        Loker cekPass = lokerService.getLokerByNomorLoker(l.getNomorLoker());
        userBookingResponse.setPassword(cekPass.getPassword());
        userBookingResponse.setKodePembayaran(p.getKodePembayaran());

        return new MessageResponse("data booking loker berhasil diinsert",userBookingResponse);
    }

    public MessageResponse updateReturningStatus(String nik, int nomorLoker){
        int deposit=0;
        BookingLoker bl = getBookingSaving(nik,nomorLoker) ;
        if(bl!=null){
            bookingLokerRepository.updateReturningStatus(nik, nomorLoker);
            lokerService.doReturning(nomorLoker);
            bl.setEndDate(new Timestamp(new Date().getTime()));
            long diff = bl.getStartDate().getTime() - bl.getEndDate().getTime();
        System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        
        if(diff>0){
            String strDiff = String.valueOf(diff);
            int intDiff = Integer.parseInt(strDiff);
            deposit = pembayaranService.getDeposit(bl.getPembayaran().getKodePembayaran())-(5000*intDiff);
            return new MessageResponse("saldo deposit terkena denda 5000/hari, sisa deposit: "+deposit);
        }
            return new MessageResponse("loker sudah dikembalikan");
        }

        return new MessageResponse("loker sedang digunakan");
    }

    public MessageResponse updateValidation(UserBookingRequest userBookingRequest){

        User u = new User();
        u.setNik(userBookingRequest.getNik());
        u.setNama(userBookingRequest.getNama());
        u.setNoHp(userBookingRequest.getNoHp());
        u.setEmail(userBookingRequest.getEmail());
        userService.updateValidation(u);

        Loker l = new Loker();
        l.setNomorLoker(userBookingRequest.getNomorLoker());
        lokerService.updateValidation(l);

        BookingLoker bookingLoker = new BookingLoker();
        
        if(getBookingSaving(u.getNik(),l.getNomorLoker())==null && getBookingReturning(u.getNik(), l.getNomorLoker())==null){
            return new MessageResponse("data booking loker tidak ada");
        }
        
        bookingLoker.setUser(u);
        bookingLoker.setLoker(l);
        bookingLokerRepository.save(bookingLoker);

        return new MessageResponse("data booking loker berhasil diupdate");
    }


    public BookingLoker getBookingSaving(String nik,int nomorLoker){
        return bookingLokerRepository.getBookingSaving(nik,nomorLoker);
    }

    public BookingLoker getBookingReturning(String nik,int nomorLoker){
        return bookingLokerRepository.getBookingReturning(nik,nomorLoker);
    }

    public BookingLoker getBookingReturningByNomorLoker(int nomorLoker){
        return bookingLokerRepository.getBookingReturningByNomorLoker(nomorLoker);
    }

    public BookingLoker getLokerByNomorLoker(int nomorLoker){
        return bookingLokerRepository.getLokerByNomorLoker(nomorLoker);
    }

    public String getKodePembayaranByNomorLoker(int nomorLoker){
        return bookingLokerRepository.getKodePembayaranByNomorLoker(nomorLoker);
    }

    public BookingLoker getBookingNotBooked(int nomorLoker){
        return bookingLokerRepository.getBookingNotBooked(nomorLoker);
    }

    public MessageResponse updateSavingStatus(int nomorLoker){
        bookingLokerRepository.updateSavingStatus(nomorLoker);
        return new MessageResponse("updating status to saving success");
    }
}
