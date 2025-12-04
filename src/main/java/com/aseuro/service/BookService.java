package com.aseuro.service;

import com.aseuro.payload.request.BookRequest;
import com.aseuro.payload.response.APIResponse;
import com.aseuro.payload.response.BookResponse;

import java.util.List;

public interface BookService {

    APIResponse<String> addBook(BookRequest request);
    APIResponse<List<BookResponse>> bookList();
    APIResponse<BookResponse> bookById(String id);


}
