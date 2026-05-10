package com.smartcart.backend.service;

import com.smartcart.backend.model.Purchase;
import com.smartcart.backend.repository.PurchaseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public List<Purchase> getUserPurchases(Long userId) {
        return purchaseRepository.findByUserIdOrderByPurchaseDateDesc(userId);
    }

    public List<Purchase> getUserPurchasesByCategory(Long userId, String category) {
        return purchaseRepository.findByUserIdAndCategory(userId, category);
    }

    public long getPurchaseCount(Long userId) {
        return purchaseRepository.countByUserId(userId);
    }

    public Purchase updatePurchase(Long id, Purchase updated) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(id);
        if (optionalPurchase.isPresent()) {
            Purchase purchase = optionalPurchase.get();
            purchase.setItemName(updated.getItemName());
            purchase.setCategory(updated.getCategory());
            purchase.setQuantity(updated.getQuantity());
            purchase.setUnit(updated.getUnit());
            purchase.setPrice(updated.getPrice());
            purchase.setSupplierName(updated.getSupplierName());
            purchase.setPurchaseDate(updated.getPurchaseDate());
            return purchaseRepository.save(purchase);
        }
        throw new RuntimeException("Purchase not found with id: " + id);
    }

    public void deletePurchase(Long id) {
        if (purchaseRepository.existsById(id)) {
            purchaseRepository.deleteById(id);
        } else {
            throw new RuntimeException("Purchase not found with id: " + id);
        }
    }
}