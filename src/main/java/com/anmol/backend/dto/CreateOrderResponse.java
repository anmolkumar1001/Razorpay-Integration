package com.anmol.backend.dto;

public record CreateOrderResponse(String orderId, int amount, String currency) { }
