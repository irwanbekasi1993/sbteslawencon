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
import sb.tes.lawencon.sbteslawencon.payload.request.UseLokerRequest;
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

        if(getBookingSaving(l.getNomorLoker())!=null && getBookingReturning( l.getNomorLoker())!=null && userService.getUserByNik(u.getNik())!=null){
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

    public MessageResponse updateReturningStatus(UseLokerRequest useLokerRequest){
        int deposit=0;
        BookingLoker bl = getBookingSaving(useLokerRequest.getNomorLoker()) ;
        User cekNik = userService.getUserByNik(bl.getUser().getNik());
        Loker cekLoker = lokerService.cekNomorLokerAndPassword(useLokerRequest.getNomorLoker(),useLokerRequest.getPassword());
        if(bl!=null && cekNik!=null && cekLoker!=null){
            bookingLokerRepository.updateReturningStatus( useLokerRequest.getNomorLoker());
            lokerService.doReturning(useLokerRequest.getNomorLoker());
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
        Loker cekPass = lokerService.getLokerByNomorLoker(l.getNomorLoker());
        lokerService.updateValidation(l);

        BookingLoker bookingLoker = new BookingLoker();
        
        if(getBookingSaving(cekPass.getNomorLoker())==null && getBookingReturning(cekPass.getNomorLoker())==null && userService.getUserByNik(u.getNik())!=null){
            return new MessageResponse("data booking loker tidak ada");
        }
        
        bookingLoker.setUser(u);
        bookingLoker.setLoker(l);
        bookingLokerRepository.save(bookingLoker);

        return new MessageResponse("data booking loker berhasil diupdate");
    }

    public MessageResponse useLoker(UseLokerRequest useLokerRequest){
    String val="";
    if(bookingLokerRepository.getBookingNotBooked(useLokerRequest.getNomorLoker())!=null){
        Loker cekLoker = lokerService.cekNomorLokerAndPassword(useLokerRequest.getNomorLoker(),useLokerRequest.getPassword());
        
        int countRetry=0;
    if(cekLoker==null){
    String getKodePembayaran = bookingLokerRepository.getKodePembayaranByNomorLoker(useLokerRequest.getNomorLoker());
    
    if(lokerService.getRetry(useLokerRequest.getNomorLoker())==0){
        countRetry=1;
    }
    if(lokerService.getRetry(useLokerRequest.getNomorLoker())<=3){
        countRetry+=lokerService.getRetry(useLokerRequest.getNomorLoker());
        lokerService.updateRetry(countRetry, useLokerRequest.getNomorLoker());
    }
                if(lokerService.getRetry(useLokerRequest.getNomorLoker())==3){
            //jumlahBiaya biayaloker = 0
            lokerService.block(useLokerRequest.getNomorLoker());
            pembayaranService.block(getKodePembayaran);
            val = "pelepasan loker berhasil";
        }
    }else{
        lokerService.updateBookedStatus(useLokerRequest.getNomorLoker());
        bookingLokerRepository.updateSavingStatus(useLokerRequest.getNomorLoker());
    
        val ="berhasil akses loker";
    }
    
    
    }else{
        val = "data booking loker tidak ada";
    }
    return new MessageResponse(val);

    
}



    public BookingLoker getBookingSaving(int nomorLoker){
        return bookingLokerRepository.getBookingSaving(nomorLoker);
    }

    public BookingLoker getBookingReturning(int nomorLoker){
        return bookingLokerRepository.getBookingReturning(nomorLoker);
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
