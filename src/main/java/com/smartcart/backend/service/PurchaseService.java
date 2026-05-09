package com.smartcart.backend.service;

import com.smartcart.backend.model.Purchase;
import com.smartcart.backend.repository.PurchaseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    // Save a new purchase
    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    // Get all purchases for a user
    public List<Purchase> getUserPurchases(Long userId) {
        return purchaseRepository.findByUserIdOrderByPurchaseDateDesc(userId);
    }

    // Get purchases by category for a user
    public List<Purchase> getUserPurchasesByCategory(Long userId, String category) {
        return purchaseRepository.findByUserIdAndCategory(userId, category);
    }

    // Get total purchases count for a user
    public long getPurchaseCount(Long userId) {
        return purchaseRepository.countByUserId(userId);
    }
}