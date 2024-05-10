package sb.tes.lawencon.sbteslawencon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sb.tes.lawencon.sbteslawencon.model.Pembayaran;
import sb.tes.lawencon.sbteslawencon.payload.response.MessageResponse;
import sb.tes.lawencon.sbteslawencon.service.PembayaranService;

@RestController
@RequestMapping("sbteslawencon/api/v1/pembayaran")
public class PembayaranController {

    @Autowired
    private PembayaranService pembayaranService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> insertPembayaran(@RequestBody Pembayaran pembayaran){
        return ResponseEntity.ok(pembayaranService.insertValidation(pembayaran));
    }

    @RequestMapping(value="/update",method = RequestMethod.PUT)
    public ResponseEntity<MessageResponse> updateBiayaLoker(@RequestBody Pembayaran pembayaran){
        return ResponseEntity.ok(pembayaranService.updateValidation(pembayaran));
    }

    @RequestMapping(value="/getKodePembayaran/{kodePembayaran}",method= RequestMethod.GET)
    public ResponseEntity<MessageResponse> getPembayaranByKodePembayaran(@PathVariable("kodePembayaran")String kodePembayaran){
        Pembayaran p = pembayaranService.getPembayaranByKodePembayaran(kodePembayaran);
        return ResponseEntity.ok(new MessageResponse("data biaya loker",p));
    }


}
