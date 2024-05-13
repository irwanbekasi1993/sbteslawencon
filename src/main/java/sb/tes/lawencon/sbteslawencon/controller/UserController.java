package sb.tes.lawencon.sbteslawencon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sb.tes.lawencon.sbteslawencon.model.User;
import sb.tes.lawencon.sbteslawencon.payload.response.MessageResponse;
import sb.tes.lawencon.sbteslawencon.service.UserService;

@RestController
@RequestMapping("sbteslawencon/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> insertUser(@RequestBody User user){
        return ResponseEntity.ok(userService.insertValidation(user));
    }

    @RequestMapping(value="/update",method = RequestMethod.PUT)
    public ResponseEntity<MessageResponse> updateUser(@RequestBody User user){
        
        return ResponseEntity.ok(userService.updateValidation(user));
    }

    @RequestMapping(value ="/getNik/{nik}",method = RequestMethod.GET)
    public ResponseEntity<MessageResponse> getUserByNik(@PathVariable("nik")String nik){
        User u = userService.getUserByNik(nik);
        return ResponseEntity.ok(new MessageResponse("data user",u));
    }

    @RequestMapping(value="/getNama/{nama}",method = RequestMethod.GET)
    public ResponseEntity<MessageResponse> getUserByNama(@PathVariable("nama")String nama){
        User u = userService.getUserByNama(nama);
        return ResponseEntity.ok(new MessageResponse("data user",u));
    }

    @RequestMapping(value ="/getNoHp/{noHp}", method = RequestMethod.GET)
    public ResponseEntity<MessageResponse> getUserByNoHp(@PathVariable("noHp")String noHp){
        User u = userService.getUserByNoHp(noHp);
        return ResponseEntity.ok(new MessageResponse("data user",u));
    }

    @RequestMapping(value="/getEmail/{email}", method = RequestMethod.GET)
    public ResponseEntity<MessageResponse> getUserByEmail(@PathVariable("email")String email){
        User u = userService.getUserByEmail(email);
        return ResponseEntity.ok(new MessageResponse("data user",u));
    }
}
