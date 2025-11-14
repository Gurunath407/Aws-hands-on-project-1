package com.aseuro.payload.response;

import com.aseuro.enums.BookStatus;

import java.time.LocalDate;

public class BorrowRecordResponse {

    private String id;
    private String bookId;
    private String userId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private BookStatus status;

    public BorrowRecordResponse(String id, String bookId, String userId,
                                LocalDate borrowDate, LocalDate returnDate,
                                BookStatus status) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public BorrowRecordResponse() {
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
}
