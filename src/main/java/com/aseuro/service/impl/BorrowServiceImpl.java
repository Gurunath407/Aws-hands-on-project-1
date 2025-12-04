package com.aseuro.service.impl;

import com.aseuro.entity.Book;
import com.aseuro.entity.BorrowRecords;
import com.aseuro.entity.User;
import com.aseuro.enums.BookStatus;
import com.aseuro.payload.response.APIResponse;
import com.aseuro.payload.response.BorrowRecordResponse;
import com.aseuro.payload.response.BorrowRecordResponseWithUser;
import com.aseuro.payload.response.UserResponse;
import com.aseuro.repository.BookRepository;
import com.aseuro.repository.BorrowRecordsRepository;
import com.aseuro.repository.UserRepository;
import com.aseuro.service.BorrowService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRecordsRepository borrowRecordsRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BorrowServiceImpl(BorrowRecordsRepository borrowRecordsRepository,
                             BookRepository bookRepository,
                             UserRepository userRepository) {
        this.borrowRecordsRepository = borrowRecordsRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public APIResponse<String> borrowBook(String userId, String bookId) {

        APIResponse<String> response = new APIResponse<>();

        // Book book = bookRepository.findById(bookId)
        //         .orElseThrow(() -> new RuntimeException("Book not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
       
               

        if (book.getQuantity() <= 0) {
            return new APIResponse<>(400, "Not Available", "Book is out of stock");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BorrowRecords record = new BorrowRecords();
        record.setId(UUID.randomUUID().toString());
        record.setBook(book);
        record.setUser(user);
        record.setBorrowDate(LocalDate.now());
        record.setStatus(BookStatus.BORROWED);

        borrowRecordsRepository.save(record);

        // update book quantity
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        return new APIResponse<>(
                201,
                "Book Borrowed",
                "Book borrowed successfully"
        );
    }

    @Override
    public APIResponse<String> returnBook(String recordId) {

        BorrowRecords record = borrowRecordsRepository.findById(recordId).get();

        record.setReturnDate(LocalDate.now());
        record.setStatus(BookStatus.RETURNED);
        borrowRecordsRepository.save(record);

        // update book quantity
        Book book = record.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        return new APIResponse<>(200, "Returned", "Book returned successfully");
    }

    @Override
    public APIResponse<List<BorrowRecordResponse>> borrowList() {

        List<BorrowRecordResponse> list = borrowRecordsRepository.findAll().stream().map(
                r -> new BorrowRecordResponse(
                        r.getId(),
                        r.getBook().getId(),
                        r.getUser().getId(),
                        r.getBorrowDate(),
                        r.getReturnDate(),
                        r.getStatus()
                )
        ).toList();

        return new APIResponse<>(200, "Borrow Records", list);
    }

    @Override
    public APIResponse<BorrowRecordResponseWithUser> recordById(String id) {

        BorrowRecords r = borrowRecordsRepository.findById(id).get();
        User user = userRepository.findById(r.getUser().getId()).get();

        return new APIResponse<>(
                200,
                "Record Details",
                new BorrowRecordResponseWithUser(
                        r.getId(),
                        r.getBook().getId(),
                        r.getUser().getId(),
                        r.getBorrowDate(),
                        r.getReturnDate(),
                        r.getStatus(),
                        new UserResponse(
                                user.getId(),
                                user.getName(),
                                user.getEmail()
                        )

                )
        );
    }
}
