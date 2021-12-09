package com.example.krkapartments.przelewy24.repository;

import com.example.krkapartments.przelewy24.data.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("transactionRepository")
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Optional<Transaction> findByMerchantIdAndPosIdAndSessionIdAndAmountAndCurrency(int merchantId, int posId, String sessionId, int originAmount, String currency);
}
