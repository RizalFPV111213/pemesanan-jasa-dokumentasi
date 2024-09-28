package Diqita.id.Database.entitas;

import java.util.Date;

public class Booking {
    private int bookingId;
    private int userId;
    private int serviceId;
    private Date bookingDate;
    private Date eventDate;
    private String status;

    public Booking(int bookingId, int userId, int serviceId, Date bookingDate, Date eventDate, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.serviceId = serviceId;
        this.bookingDate = bookingDate;
        this.eventDate = eventDate;
        this.status = status;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
