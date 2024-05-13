package sb.tes.lawencon.sbteslawencon.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import sb.tes.lawencon.sbteslawencon.model.Loker;

@Repository
@Transactional
public interface LokerRepository extends JpaRepository<Loker,Integer>{
    @Query(value="select * from loker where nomor_loker=:nomorLoker and status!='blocked'",nativeQuery=true)
    Loker existsByNomorLoker(@Param("nomorLoker") int nomorLoker);

    @Modifying
    @Query(value="update loker set status='booked' where nomor_loker=:nomorLoker and status!='blocked'",nativeQuery=true)
    int updateBookedStatus(@Param("nomorLoker")int nomorLoker);

    @Modifying
    @Query(value="update loker set status='not booked' where nomor_loker=:nomorLoker and status!='block' ",nativeQuery=true)
    int updateNotBookedStatus(@Param("nomorLoker")int nomorLoker);


    @Query(value = "select * from loker where nomor_loker=:nomorLoker and password=:password and status!='blocked'",nativeQuery = true)
    Loker cekNomorLokerAndPassword(@Param("nomorLoker")int nomorLoker, @Param("password")int password);
    

    @Modifying
    @Query(value="update loker set retry=:retry where nomor_loker=:nomorLoker and status!='block' ",nativeQuery = true)
    int updateRetry(@Param("retry")int retry,@Param("nomorLoker")int nomorLoker);

    @Query(value="select retry from loker where nomor_loker=:nomorLoker",nativeQuery = true)
    int getRetry(@Param("nomorLoker")int nomorLoker);

    @Modifying
    @Query(value="update loker set status='block' where retry=3 and nomor_loker=:nomorLoker",nativeQuery=true)
    int block(@Param("nomorLoker")int nomorLoker);



}