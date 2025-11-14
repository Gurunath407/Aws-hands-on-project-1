package com.aseuro.service;


import com.aseuro.payload.response.APIResponse;
import com.aseuro.payload.response.BorrowRecordResponse;
import com.aseuro.payload.response.BorrowRecordResponseWithUser;

import java.util.List;

public interface BorrowService {

    APIResponse<String> borrowBook(String userId, String bookId);
    APIResponse<String> returnBook(String recordId);
    APIResponse<List<BorrowRecordResponse>> borrowList();
    APIResponse<BorrowRecordResponseWithUser> recordById(String id);
}
