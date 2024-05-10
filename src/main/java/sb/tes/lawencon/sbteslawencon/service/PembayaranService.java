package sb.tes.lawencon.sbteslawencon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sb.tes.lawencon.sbteslawencon.model.Pembayaran;
import sb.tes.lawencon.sbteslawencon.payload.response.MessageResponse;
import sb.tes.lawencon.sbteslawencon.repository.PembayaranRepository;

@Service
public class PembayaranService {

@Autowired
private PembayaranRepository pembayaranRepository;

public MessageResponse insertValidation(Pembayaran pembayaran){
    if(getPembayaranByKodePembayaran(pembayaran.getKodePembayaran())!=null){
        return new MessageResponse("data pembayaran loker sudah ada");
    }

    pembayaranRepository.save(pembayaran);
    return new MessageResponse("data pembayaran berhasil diinput");
}

public MessageResponse updateValidation(Pembayaran pembayaran){
    if(getPembayaranByKodePembayaran(pembayaran.getKodePembayaran())==null){
        return new MessageResponse("data pembayaran loker tidak ditemukan");
    }
    pembayaranRepository.save(pembayaran);
    return new MessageResponse("data pembayaran berhasil diupdate");
}


public Pembayaran getPembayaranByKodePembayaran(String kodePembayaran){
    return pembayaranRepository.existsByKodePembayaran(kodePembayaran);
}

public MessageResponse block(String kodePembayaran){
    if(pembayaranRepository.getDeposit(kodePembayaran)!=0){
        pembayaranRepository.block(kodePembayaran);
        return new MessageResponse("pembebasan loker");
    }
    return null;
    
}

public MessageResponse payDeposit(int jumlahPembayaran,String kodePembayaran){
    pembayaranRepository.payDeposit(jumlahPembayaran, kodePembayaran);
    return new MessageResponse("pembayaran deposit: "+jumlahPembayaran+" , kodePembayaran:"+kodePembayaran);
}

public int getDeposit(String kodePembayaran){
    return pembayaranRepository.getDeposit(kodePembayaran);
}

public int countPembayaran(){
    return pembayaranRepository.countPembayaran();
}

}
