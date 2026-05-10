package com.smartcart.backend.dto;

public class LoginResponse {
    private Long userId;
    private String fullName;
    private String phoneNumber;
    private String marketName;

    public LoginResponse(Long userId, String fullName, String phoneNumber, String marketName) {
        this.userId = userId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.marketName = marketName;
    }

    public Long getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getMarketName() { return marketName; }
}