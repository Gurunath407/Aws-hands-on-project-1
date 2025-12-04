package com.aseuro.service.impl;

import com.aseuro.entity.Book;
import com.aseuro.payload.request.BookRequest;
import com.aseuro.payload.response.APIResponse;
import com.aseuro.payload.response.BookResponse;
import com.aseuro.repository.BookRepository;
import com.aseuro.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public APIResponse<String> addBook(BookRequest request) {
        APIResponse<String> response=new APIResponse<>();

        Book save = bookRepository.save(new Book(
                UUID.randomUUID().toString(),
                request.getTitle(),
                request.getAuthor(),
                request.getIsbn(),
                request.getQuantity(),
                request.getStatus()
        ));

        if(save!=null){
            response.setStatus(201);
            response.setMessage("Book added");
            response.setData(request.getTitle() + "book is added");
            return response;
        }

        response.setStatus(201);
        response.setMessage("Request failed");
        response.setData("Error during book registration process");
        return response;
    }

    @Override
    public APIResponse<List<BookResponse>> bookList() {
        List<BookResponse> list = bookRepository.findAll().stream().map(book -> {
            return new BookResponse(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getIsbn(),
                    book.getStatus()
            );
        }).toList();

        return new APIResponse<>(
                200,
                "Book List",
                list
        );
    }

    @Override
    public APIResponse<BookResponse> bookById(String id) {
        return bookRepository.findById(id)
                .map(book -> new APIResponse<>(
                        200,
                        book.getTitle() + " Data",
                        new BookResponse(
                                book.getId(),
                                book.getTitle(),
                                book.getAuthor(),
                                book.getIsbn(),
                                book.getStatus()
                        )
                ))
                .orElse(new APIResponse<>(
                        404,
                        "Book not found",
                        null
                ));
    }

    @Override
    public APIResponse<String> updateBook(String id, BookRequest request) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    // Only update fields that are not null to allow partial updates
                    if (request.getTitle() != null) {
                        existingBook.setTitle(request.getTitle());
                    }
                    if (request.getAuthor() != null) {
                        existingBook.setAuthor(request.getAuthor());
                    }
                    if (request.getIsbn() != null) {
                        existingBook.setIsbn(request.getIsbn());
                    }
                    if (request.getQuantity() != null) {
                        existingBook.setQuantity(request.getQuantity());
                    }
                    if (request.getStatus() != null) {
                        existingBook.setStatus(request.getStatus());
                    }

                    bookRepository.save(existingBook);
                    return new APIResponse<>(
                            200,
                            "Book updated successfully",
                            "Book with ID " + id + " has been updated"
                    );
                })
                .orElse(new APIResponse<>(
                        404,
                        "Book not found",
                        "No book found with ID: " + id
                ));
    }

    @Override
    public APIResponse<String> deleteBook(String id) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.delete(book);
                    return new APIResponse<>(
                            200,
                            "Book deleted successfully",
                            "Book with ID " + id + " has been deleted"
                    );
                })
                .orElse(new APIResponse<>(
                        404,
                        "Book not found",
                        "No book found with ID: " + id
                ));
    }
}
