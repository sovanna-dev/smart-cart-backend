package com.smartcart.backend.repository;

import com.smartcart.backend.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    // Find all purchases for a specific user, ordered by date (newest first)
    List<Purchase> findByUserIdOrderByPurchaseDateDesc(Long userId);

    // Find purchases by user and category
    List<Purchase> findByUserIdAndCategory(Long userId, String category);

    // Count purchases for a user
    long countByUserId(Long userId);
}