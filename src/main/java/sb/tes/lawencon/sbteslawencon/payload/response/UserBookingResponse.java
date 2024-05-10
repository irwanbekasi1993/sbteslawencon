package sb.tes.lawencon.sbteslawencon.payload.response;

import java.io.Serializable;

public class UserBookingResponse implements Serializable{
    private String nik;
    private String nama;
    private String noHp;
    private String email;
    private int nomorLoker;
    private int password;
    private String kodePembayaran;

    public UserBookingResponse() {
    }
    public UserBookingResponse(String nik, String nama, 
    String noHp, String email, int nomorLoker, int password,
    String kodePembayaran) {
        this.nik = nik;
        this.nama = nama;
        this.noHp = noHp;
        this.email = email;
        this.nomorLoker = nomorLoker;
        this.password = password;
        this.kodePembayaran = kodePembayaran;
    }

    

    public String getNik() {
        return nik;
    }
    public void setNik(String nik) {
        this.nik = nik;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNoHp() {
        return noHp;
    }
    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getNomorLoker() {
        return nomorLoker;
    }
    public void setNomorLoker(int nomorLoker) {
        this.nomorLoker = nomorLoker;
    }
    public int getPassword() {
        return password;
    }
    public void setPassword(int password) {
        this.password = password;
    }
    public String getKodePembayaran() {
        return kodePembayaran;
    }
    public void setKodePembayaran(String kodePembayaran) {
        this.kodePembayaran = kodePembayaran;
    }

    
}
