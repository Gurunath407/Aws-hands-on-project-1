package com.aseuro.payload.response;

import com.aseuro.enums.BookStatus;

import java.time.LocalDate;

public class BorrowRecordResponseWithUser {

    private String id;
    private String bookId;
    private String userId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private BookStatus status;
    private UserResponse user;

    public BorrowRecordResponseWithUser(String id, String bookId, String userId, LocalDate borrowDate, LocalDate returnDate, BookStatus status, UserResponse user) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
        this.user = user;
    }

    public BorrowRecordResponseWithUser() {
    }

    // ✅ ADD GETTERS

    public String getId() {
        return id;
    }

    public String getBookId() {
        return bookId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public BookStatus getStatus() {
        return status;
    }

    // (Optional) setters if needed


    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }
}
