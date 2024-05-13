package sb.tes.lawencon.sbteslawencon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sb.tes.lawencon.sbteslawencon.model.Loker;
import sb.tes.lawencon.sbteslawencon.payload.request.UseLokerRequest;
import sb.tes.lawencon.sbteslawencon.payload.response.MessageResponse;
import sb.tes.lawencon.sbteslawencon.service.LokerService;

@RestController
@RequestMapping("sbteslawencon/api/v1/loker")
public class LokerController {

    @Autowired
    private LokerService lokerService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> insertLoker(@RequestBody Loker loker){
        return ResponseEntity.ok(lokerService.insertValidation(loker));
    }

    @RequestMapping(value="/update",method = RequestMethod.PUT)
    public ResponseEntity<MessageResponse> updateLoker(@RequestBody Loker loker){
        return ResponseEntity.ok(lokerService.updateValidation(loker));
    }

    @RequestMapping(value="/getNomorLoker/{nomorLoker}", method = RequestMethod.GET)
    public ResponseEntity<MessageResponse> getLokerByNomorLoker(@PathVariable("nomorLoker")int nomorLoker){
        Loker l = lokerService.getLokerByNomorLoker(nomorLoker);
        return ResponseEntity.ok(new MessageResponse("data loker",l));
    }



}
