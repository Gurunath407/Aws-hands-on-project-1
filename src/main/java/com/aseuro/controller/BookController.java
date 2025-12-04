package com.aseuro.controller;

import com.aseuro.payload.request.BookRequest;
import com.aseuro.payload.response.APIResponse;
import com.aseuro.payload.response.BookResponse;
import com.aseuro.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    public ResponseEntity<APIResponse<String>> addBook(@RequestBody BookRequest request) {
        APIResponse<String> response = bookService.addBook(request);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @RequestMapping
    public ResponseEntity<APIResponse<List<BookResponse>>> bookList() {
        APIResponse<List<BookResponse>> response = bookService.bookList();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<BookResponse>> bookById(@PathVariable String id) {
        APIResponse<BookResponse> response = bookService.bookById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<String>> updateBook(@PathVariable String id, @RequestBody BookRequest request) {
        APIResponse<String> response = bookService.updateBook(id, request);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteBook(@PathVariable String id) {
        APIResponse<String> response = bookService.deleteBook(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
