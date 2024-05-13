package sb.tes.lawencon.sbteslawencon.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name="booking_loker")
public class BookingLoker {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "nik")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="nomorLoker")
    private Loker loker;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="kodePembayaran")
    private Pembayaran pembayaran;

    private Timestamp startDate;
    private Timestamp endDate;

    private String statusBooking;

    public BookingLoker() {
    }

    public BookingLoker(Long id, User user, Loker loker, 
    Timestamp startDate,Timestamp endDate,String statusBooking) {
        this.id = id;
        this.user = user;
        this.loker = loker;
        this.statusBooking=statusBooking;
        this.startDate=startDate;
        this.endDate=endDate;
    }

    

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Loker getLoker() {
        return loker;
    }

    public void setLoker(Loker loker) {
        this.loker = loker;
    }


    public String getStatusBooking() {
        return statusBooking;
    }

    public void setStatusBooking(String statusBooking) {
        this.statusBooking = statusBooking;
    }

    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }

    
}
