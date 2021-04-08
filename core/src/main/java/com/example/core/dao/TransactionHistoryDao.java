package com.example.core.dao;

import com.example.common.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryDao extends JpaRepository<TransactionHistory, Long> {
}
