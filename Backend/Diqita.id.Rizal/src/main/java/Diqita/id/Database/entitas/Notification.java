package Diqita.id.Database.entitas;

import java.util.Date;

public class Notification {
    private int notificationId;
    private int userId;
    private String message;
    private Date dateSent;
    private boolean isRead;

    public Notification(int notificationId, int userId, String message, Date dateSent, boolean isRead) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.dateSent = dateSent;
        this.isRead = isRead;
    }

    // Getters and Setters
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
