package sb.tes.lawencon.sbteslawencon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sb.tes.lawencon.sbteslawencon.model.User;
import sb.tes.lawencon.sbteslawencon.payload.response.MessageResponse;
import sb.tes.lawencon.sbteslawencon.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public MessageResponse insertValidation(User user){
        if(userRepository.existsByNik(user.getNik())!=null){
            return new MessageResponse("data nik sudah ada",user.getNik());
        }
        if(userRepository.existsByNama(user.getNama())!=null){
            return new MessageResponse("data nama sudah ada",user.getNama());
        }
        if(userRepository.existsByNoHp(user.getNoHp())!=null){
            return new MessageResponse("data noHp sudah ada",user.getNoHp());
        }
        if(userRepository.existsByEmail(user.getEmail())!=null){
            return new MessageResponse("data email sudah ada",user.getEmail());
        }
        userRepository.save(user);
        return new MessageResponse("data user berhasil diinsert");
    }

    public MessageResponse updateValidation(User user){
        if(getUserByNik(user.getNik())==null){
            return new MessageResponse("data nik tidak ada",user.getNik());
        }
        if(getUserByNama(user.getNama())==null){
            return new MessageResponse("data nama tidak ada",user.getNama());
        }
        if(getUserByNoHp(user.getNoHp())==null){
            return new MessageResponse("data noHp tidak ada",user.getNoHp());
        }
        if(getUserByEmail(user.getEmail())==null){
            return new MessageResponse("data email tidak ada",user.getEmail());
        }
        userRepository.save(user);
        return new MessageResponse("data user berhasil diupdate");
    }


    public User getUserByNik(String nik){
        return userRepository.existsByNik(nik);
    }

    public User getUserByNama(String nama){
        return userRepository.existsByNama(nama);
    }

    public User getUserByNoHp(String noHp){
        return userRepository.existsByNoHp(noHp);
    }

    public User getUserByEmail(String email){
        return userRepository.existsByEmail(email);
    }
}
