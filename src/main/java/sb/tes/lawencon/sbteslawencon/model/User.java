package sb.tes.lawencon.sbteslawencon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {

    @Id
    private String nik;

    private String nama;

    private String noHp;

    private String email;

    public User() {
    }

    

    public User(String nik, String nama, String noHp, String email) {
        this.nik = nik;
        this.nama = nama;
        this.noHp = noHp;
        this.email = email;
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

    

}
