package com.aseuro.repository;

import com.aseuro.entity.BorrowRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordsRepository extends JpaRepository<BorrowRecords, String> {
}