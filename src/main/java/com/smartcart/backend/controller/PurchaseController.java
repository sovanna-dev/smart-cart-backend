package com.smartcart.backend.controller;

import com.smartcart.backend.model.Purchase;
import com.smartcart.backend.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    // Save a new purchase
    @PostMapping
    public ResponseEntity<Purchase> addPurchase(@RequestBody Purchase purchase) {
        Purchase savedPurchase = purchaseService.savePurchase(purchase);
        return ResponseEntity.ok(savedPurchase);
    }

    // Get all purchases for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Purchase>> getUserPurchases(@PathVariable Long userId) {
        List<Purchase> purchases = purchaseService.getUserPurchases(userId);
        return ResponseEntity.ok(purchases);
    }

    // Get purchases by category for a user
    @GetMapping("/user/{userId}/category/{category}")
    public ResponseEntity<List<Purchase>> getUserPurchasesByCategory(
            @PathVariable Long userId, 
            @PathVariable String category) {
        List<Purchase> purchases = purchaseService.getUserPurchasesByCategory(userId, category);
        return ResponseEntity.ok(purchases);
    }

    // Get purchase count for a user
    @GetMapping("/count/{userId}")
    public ResponseEntity<Long> getPurchaseCount(@PathVariable Long userId) {
        long count = purchaseService.getPurchaseCount(userId);
        return ResponseEntity.ok(count);
    }
}