package com.smartcart.backend.controller;

import com.smartcart.backend.dto.ApiResponse;
import com.smartcart.backend.dto.PurchaseResponse;
import com.smartcart.backend.model.Purchase;
import com.smartcart.backend.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addPurchase(@RequestBody Purchase purchase) {
        Purchase saved = purchaseService.savePurchase(purchase);
        PurchaseResponse response = toResponse(saved);
        return ResponseEntity.ok(ApiResponse.success("Purchase saved", response));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getUserPurchases(@PathVariable Long userId) {
        List<Purchase> purchases = purchaseService.getUserPurchases(userId);
        List<PurchaseResponse> responseList = purchases.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success("Purchases retrieved", responseList));
    }

    @GetMapping("/user/{userId}/category/{category}")
    public ResponseEntity<ApiResponse> getUserPurchasesByCategory(
            @PathVariable Long userId,
            @PathVariable String category) {
        List<Purchase> purchases = purchaseService.getUserPurchasesByCategory(userId, category);
        List<PurchaseResponse> responseList = purchases.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success("Purchases by category", responseList));
    }

    @GetMapping("/count/{userId}")
    public ResponseEntity<ApiResponse> getPurchaseCount(@PathVariable Long userId) {
        long count = purchaseService.getPurchaseCount(userId);
        return ResponseEntity.ok(ApiResponse.success("Count retrieved", count));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePurchase(@PathVariable Long id, @RequestBody Purchase updated) {
        try {
            Purchase saved = purchaseService.updatePurchase(id, updated);
            return ResponseEntity.ok(ApiResponse.success("Purchase updated", toResponse(saved)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePurchase(@PathVariable Long id) {
        try {
            purchaseService.deletePurchase(id);
            return ResponseEntity.ok(ApiResponse.success("Purchase deleted"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    // Helper to convert Purchase entity to PurchaseResponse DTO
    private PurchaseResponse toResponse(Purchase purchase) {
        return new PurchaseResponse(
            purchase.getId(),
            purchase.getUserId(),
            purchase.getItemName(),
            purchase.getCategory(),
            purchase.getQuantity(),
            purchase.getUnit(),
            purchase.getPrice(),
            purchase.getSupplierName(),
            purchase.getPurchaseDate()
        );
    }
}