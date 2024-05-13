package sb.tes.lawencon.sbteslawencon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="loker")
public class Loker {

    @Id
    private int nomorLoker;

    private int password;

    private String status;
    private int retry;

    public Loker() {
    }

    public Loker(int nomorLoker, int password, String status,int retry) {
        this.nomorLoker = nomorLoker;
        this.password = password;
        this.status=status;
        this.retry=retry;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    
    
    
}
