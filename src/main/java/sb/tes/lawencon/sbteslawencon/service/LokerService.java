package sb.tes.lawencon.sbteslawencon.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sb.tes.lawencon.sbteslawencon.model.Loker;
import sb.tes.lawencon.sbteslawencon.payload.request.UseLokerRequest;
import sb.tes.lawencon.sbteslawencon.payload.response.MessageResponse;
import sb.tes.lawencon.sbteslawencon.repository.LokerRepository;

@Service
public class LokerService {

    @Autowired
    private LokerRepository lokerRepository;



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

public Loker cekNomorLokerAndPassword(int nomorLoker,int password){
    return lokerRepository.cekNomorLokerAndPassword(nomorLoker, password);
}

public int updateBookedStatus(int nomorLoker){
    return lokerRepository.updateBookedStatus(nomorLoker);
}

}
