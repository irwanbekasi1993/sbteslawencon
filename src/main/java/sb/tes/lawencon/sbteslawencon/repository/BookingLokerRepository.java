package sb.tes.lawencon.sbteslawencon.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sb.tes.lawencon.sbteslawencon.model.BookingLoker;

@Repository
@Transactional
public interface BookingLokerRepository extends JpaRepository<BookingLoker,Long>{

   @Query(value = "select * from booking_loker where nomor_loker=:nomorLoker",nativeQuery = true)
   BookingLoker getLokerByNomorLoker(@Param("nomorLoker")int nomorLoker);

    @Query(value="select kode_pembayaran from booking_loker where nomor_loker=:nomorLoker",nativeQuery = true)
    String getKodePembayaranByNomorLoker(@Param("nomorLoker")int nomorLoker);

    @Query(value="select * from booking_loker where status_booking='saving'",nativeQuery = true)
    List<BookingLoker> getAllSaving();

    @Query(value="select * from booking_loker where status_booking='returning'",nativeQuery = true)
    List<BookingLoker> getAllReturning();

    @Modifying
    @Query(value="update booking_loker set status_booking='not booked', end_date=current_timestamp where nik=:nik and nomor_loker=:nomorLoker ",nativeQuery = true)
    int updateReturningStatus(@Param("nik")String nik,@Param("nomorLoker")int nomorLoker);

    @Query(value="select * from booking_loker where status_booking='not booked' and nomor_loker=:nomorLoker",nativeQuery = true)
    BookingLoker getBookingNotBooked(@Param("nomorLoker")int nomorLoker);

    @Modifying
    @Query(value="update booking_loker set status_booking='saving', start_date=current_timestamp where nomor_loker=:nomorLoker and status_booking='not booked'",nativeQuery = true)
    int updateSavingStatus(@Param("nomorLoker")int nomorLoker);


    @Query(value="select * from booking_loker where nik=:nik and status_booking='saving' and nomor_loker=:nomorLoker",nativeQuery = true)
    BookingLoker getBookingSaving(@Param("nik")String nik,@Param("nomorLoker")int nomorLoker);

    @Query(value="select * from booking_loker where nik=:nik and status_booking='returning' and nomor_loker=:nomorLoker",nativeQuery = true)
    BookingLoker getBookingReturning(@Param("nik")String nik,@Param("nomorLoker")int nomorLoker);

    @Query(value="select * from booking_loker where nomor_loker=:nomorLoker and status_booking='returning'",nativeQuery=true)
    BookingLoker getBookingReturningByNomorLoker(@Param("nomorLoker")int nomorLoker);

}
