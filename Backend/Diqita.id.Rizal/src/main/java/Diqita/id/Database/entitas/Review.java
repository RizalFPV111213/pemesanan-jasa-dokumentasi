package Diqita.id.Database.entitas;

import java.util.Date;

public class Review {
    private int reviewId;
    private int bookingId;
    private int rating;
    private String comments;
    private Date reviewDate;

    public Review(int reviewId, int bookingId, int rating, String comments, Date reviewDate) {
        this.reviewId = reviewId;
        this.bookingId = bookingId;
        this.rating = rating;
        this.comments = comments;
        this.reviewDate = reviewDate;
    }

    // Getters and Setters
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}

