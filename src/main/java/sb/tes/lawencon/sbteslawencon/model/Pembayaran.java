package sb.tes.lawencon.sbteslawencon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="pembayaran")
public class Pembayaran {

    @Id
    private String kodePembayaran;

    private String jenisPembayaran;

    private int jumlahPembayaran;

    public Pembayaran() {
    }

    public Pembayaran(String kodePembayaran, String jenisPembayaran, int jumlahPembayaran) {
        this.kodePembayaran = kodePembayaran;
        this.jenisPembayaran = jenisPembayaran;
        this.jumlahPembayaran = jumlahPembayaran;
    }

    public String getKodePembayaran() {
        return kodePembayaran;
    }

    public void setKodePembayaran(String kodePembayaran) {
        this.kodePembayaran = kodePembayaran;
    }

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    public int getJumlahPembayaran() {
        return jumlahPembayaran;
    }

    public void setJumlahPembayaran(int jumlahPembayaran) {
        this.jumlahPembayaran = jumlahPembayaran;
    }


    
}
