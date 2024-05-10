package sb.tes.lawencon.sbteslawencon.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sb.tes.lawencon.sbteslawencon.model.BookingLoker;
import sb.tes.lawencon.sbteslawencon.model.Loker;
import sb.tes.lawencon.sbteslawencon.model.Pembayaran;
import sb.tes.lawencon.sbteslawencon.payload.request.UseLokerRequest;
import sb.tes.lawencon.sbteslawencon.payload.response.MessageResponse;
import sb.tes.lawencon.sbteslawencon.repository.LokerRepository;

@Service
public class LokerService {

    @Autowired
    private LokerRepository lokerRepository;

    @Autowired
    private BookingLokerService bookingLokerService;


    @Autowired
    private PembayaranService pembayaranService;

public MessageResponse insertValidation(Loker loker){
    if(getLokerByNomorLoker(loker.getNomorLoker())!=null){
        return new MessageResponse("data loker sudah ada");
    }
    loker.setPassword(otpGenerator());
    loker.setStatus("Not Booked");
        lokerRepository.save(loker);    
    
    
    return new MessageResponse("data loker berhasil diinsert");
}

public MessageResponse doReturning(int nomorLoker){
        lokerRepository.updateNotBookedStatus(nomorLoker);
        return new MessageResponse("loker telah dikembalikan dengan kode loker: "+nomorLoker);
    
}

public MessageResponse updateValidation(Loker loker){
    if(getLokerByNomorLoker(loker.getNomorLoker())==null){
        return new MessageResponse("data loker tidak ditemukan");
    }
    loker.setPassword(otpGenerator());
        lokerRepository.save(loker);
    return new MessageResponse("data loker berhasil diupdate");
}

public MessageResponse useLoker(UseLokerRequest useLokerRequest){
    String val="";
    if(bookingLokerService.getBookingNotBooked(useLokerRequest.getNomorLoker())!=null){
        Loker cekLoker = lokerRepository.cekNomorLokerAndPassword(useLokerRequest.getNomorLoker(),useLokerRequest.getPassword());
        
        int countRetry=0;
    if(cekLoker==null){
    String getKodePembayaran = bookingLokerService.getKodePembayaranByNomorLoker(useLokerRequest.getNomorLoker());
    
    if(getRetry(useLokerRequest.getNomorLoker())==0){
        countRetry=1;
    }
    if(getRetry(useLokerRequest.getNomorLoker())<=3){
        countRetry+=getRetry(useLokerRequest.getNomorLoker());
        updateRetry(countRetry, useLokerRequest.getNomorLoker());
    }
                if(getRetry(useLokerRequest.getNomorLoker())==3){
            //jumlahBiaya biayaloker = 0
            block(useLokerRequest.getNomorLoker());
            pembayaranService.block(getKodePembayaran);
            val = "pelepasan loker berhasil";
        }
    }else{
        lokerRepository.updateBookedStatus(useLokerRequest.getNomorLoker());
        bookingLokerService.updateSavingStatus(useLokerRequest.getNomorLoker());
    
        val ="berhasil akses loker";
    }
    
    
    }else{
        val = "data booking loker tidak ada";
    }
    return new MessageResponse(val);

    
}


public int otpGenerator(){
    Random random = new Random();
    int otp = 100000+random.nextInt(900000);
    return otp;
}

public Loker getLokerByNomorLoker(int nomorLoker){
    return lokerRepository.existsByNomorLoker(nomorLoker);    
}

public MessageResponse updateRetry(int retry,int nomorLoker){
    lokerRepository.updateRetry(retry,nomorLoker);
    return new MessageResponse("jumlah retry: "+retry);
}

public int getRetry(int nomorLoker){
    return lokerRepository.getRetry(nomorLoker);
}

public MessageResponse block(int nomorLoker){
    lokerRepository.block(nomorLoker);
    return new MessageResponse("mohon maaf anda diblock: ");
}


}
