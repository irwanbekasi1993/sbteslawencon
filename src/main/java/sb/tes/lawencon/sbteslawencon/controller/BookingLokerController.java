package sb.tes.lawencon.sbteslawencon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sb.tes.lawencon.sbteslawencon.model.BookingLoker;
import sb.tes.lawencon.sbteslawencon.payload.request.UserBookingRequest;
import sb.tes.lawencon.sbteslawencon.payload.response.MessageResponse;
import sb.tes.lawencon.sbteslawencon.payload.response.UserBookingResponse;
import sb.tes.lawencon.sbteslawencon.service.BookingLokerService;


@RestController
@RequestMapping("sbteslawencon/api/v1/bookingLoker")
public class BookingLokerController {

    @Autowired
    private BookingLokerService bookingLokerService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> insertBookingLoker(@RequestBody UserBookingRequest userBookingRequest){
        return ResponseEntity.ok(bookingLokerService.insertValidation(userBookingRequest));
    }

    @RequestMapping(value="/update",method = RequestMethod.PUT)
    public ResponseEntity<MessageResponse> updateBookingLoker(@RequestBody UserBookingRequest userBookingRequest){
        return ResponseEntity.ok(bookingLokerService.updateValidation(userBookingRequest));
    }

    @RequestMapping(value = "/getSaving/{nik}/{kodeLoker}", method = RequestMethod.GET)
    public ResponseEntity<MessageResponse> getSaving(@PathVariable("nik")String nik,@PathVariable("kodeLoker")int nomorLoker){
        
        UserBookingResponse userBookingResponse = new UserBookingResponse();
            BookingLoker bl = bookingLokerService.getBookingSaving(nik, nomorLoker);
            if(bl!=null){
                userBookingResponse.setNik(nik);
                userBookingResponse.setNomorLoker(nomorLoker);
            }
        return ResponseEntity.ok(new MessageResponse("data booking loker: ",userBookingResponse));
    }

    @RequestMapping(value = "/getReturning/{nik}/{nomorLoker}", method = RequestMethod.GET)
    public ResponseEntity<MessageResponse> getReturning(@PathVariable("nik")String nik,@PathVariable("nomorLoker")int nomorLoker){
        BookingLoker bl = bookingLokerService.getBookingReturning(nik, nomorLoker);
        return ResponseEntity.ok(new MessageResponse("data loker: ",bl));
    }

    @RequestMapping(value = "/return", method=RequestMethod.PUT)
    public ResponseEntity<MessageResponse> requestMethodName(@RequestBody UserBookingRequest userBookingRequest) {
        return ResponseEntity.ok(bookingLokerService.updateReturningStatus(userBookingRequest.getNik(), userBookingRequest.getNomorLoker()));
    }
    
}
