package sb.tes.lawencon.sbteslawencon.payload.request;

import java.io.Serializable;

public class UserBookingRequest implements Serializable{
private String nik;
private String nama;
private String noHp;
private String email;
private int nomorLoker;
private String jenisPembayaran;
private int jumlahPembayaran;


public UserBookingRequest() {
}
public UserBookingRequest(String nik, String nama, String noHp, 
String email, int nomorLoker,String jenisPembayaran,int jumlahPembayaran) {
    this.nik = nik;
    this.nama = nama;
    this.noHp = noHp;
    this.email = email;
    this.nomorLoker = nomorLoker;
    this.jenisPembayaran = jenisPembayaran;
    this.jumlahPembayaran=jumlahPembayaran;
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
public void setNomorLoker(int kodeLoker) {
    this.nomorLoker = kodeLoker;
}


}
