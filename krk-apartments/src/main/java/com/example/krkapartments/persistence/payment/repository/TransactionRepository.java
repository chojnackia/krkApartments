package com.example.krkapartments.persistence.payment.repository;

import com.example.krkapartments.persistence.payment.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("transactionRepository")
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    Optional<TransactionEntity> findByMerchantIdAndPosIdAndSessionIdAndAmountAndCurrency(int merchantId, int posId, String sessionId, int originAmount, String currency);
}
