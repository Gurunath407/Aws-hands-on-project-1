package com.aseuro.controller;

import com.aseuro.payload.response.APIResponse;

import com.aseuro.payload.response.BorrowRecordResponse;
import com.aseuro.payload.response.BorrowRecordResponseWithUser;
import com.aseuro.service.BorrowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping("/borrow-book/{userId}/{bookId}")
    public ResponseEntity<APIResponse<String>> borrowBook(
            @PathVariable String userId,
            @PathVariable String bookId) {

        APIResponse<String> response = borrowService.borrowBook(userId, bookId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @PostMapping("/return-book/{recordId}")
    public ResponseEntity<APIResponse<String>> returnBook(@PathVariable String recordId) {
        APIResponse<String> response = borrowService.returnBook(recordId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping("/records")
    public ResponseEntity<APIResponse<List<BorrowRecordResponse>>> records() {
        APIResponse<List<BorrowRecordResponse>> response = borrowService.borrowList();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<BorrowRecordResponseWithUser>> recordById(@PathVariable String id) {
        APIResponse<BorrowRecordResponseWithUser> response = borrowService.recordById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
