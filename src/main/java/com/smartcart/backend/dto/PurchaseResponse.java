package com.smartcart.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PurchaseResponse {
    private Long id;
    private Long userId;
    private String itemName;
    private String category;
    private BigDecimal quantity;
    private String unit;
    private BigDecimal price;
    private String supplierName;
    private LocalDate purchaseDate;

    public PurchaseResponse(Long id, Long userId, String itemName, String category,
                           BigDecimal quantity, String unit, BigDecimal price,
                           String supplierName, LocalDate purchaseDate) {
        this.id = id;
        this.userId = userId;
        this.itemName = itemName;
        this.category = category;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.supplierName = supplierName;
        this.purchaseDate = purchaseDate;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getItemName() { return itemName; }
    public String getCategory() { return category; }
    public BigDecimal getQuantity() { return quantity; }
    public String getUnit() { return unit; }
    public BigDecimal getPrice() { return price; }
    public String getSupplierName() { return supplierName; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
}