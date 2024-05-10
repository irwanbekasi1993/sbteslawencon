package sb.tes.lawencon.sbteslawencon.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sb.tes.lawencon.sbteslawencon.model.Pembayaran;



@Repository
@Transactional
public interface PembayaranRepository extends JpaRepository<Pembayaran,String>{
    
    @Query(value="select * from pembayaran where kode_pembayaran=:kodePembayaran",nativeQuery=true)
    Pembayaran existsByKodePembayaran(@Param("kodePembayaran")String kodePembayaran);

    @Modifying
    @Query(value="update pembayaran set jumlah_pembayaran=:jumlahPembayaran where jenis_pembayaran='deposit' and kode_pembayaran=:kodePembayaran",nativeQuery = true)
    int payDeposit(@Param("jumlahPembayaran")int jumlahPembayaran, @Param("kodePembayaran")String kodePembayaran);

    @Query(value="select jumlah_pembayaran from pembayaran where jenis_pembayaran='deposit' and kode_pembayaran=:kodePembayaran",nativeQuery = true)
    int getDeposit(@Param("kodePembayaran")String kodePembayaran);

    @Modifying
    @Query(value="update pembayaran set jumlah_pembayaran=0 where kode_pembayaran=:kodePembayaran",nativeQuery = true)
    int block(@Param("kodePembayaran")String kodePembayaran);

    @Query(value="select count(kode_pembayaran) from pembayaran",nativeQuery = true)
    int countPembayaran();
}
