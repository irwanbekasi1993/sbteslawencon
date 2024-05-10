package sb.tes.lawencon.sbteslawencon.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;


import sb.tes.lawencon.sbteslawencon.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,String>{

    @Query(value="select * from users where nik=:nik",nativeQuery=true)
    User existsByNik(@Param("nik") String nik);

    @Query(value="select * from users where nama=:nama",nativeQuery=true)
    User existsByNama(@Param("nama") String nama);

    @Query(value="select * from users where no_hp=:noHp",nativeQuery=true)
    User existsByNoHp(@Param("noHp") String noHp);

    @Query(value="select * from users where email=:email",nativeQuery=true)
    User existsByEmail(@Param("email") String email);

}

