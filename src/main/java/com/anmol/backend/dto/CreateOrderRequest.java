package com.anmol.backend.dto;

public record CreateOrderRequest (long amountRupees, String currency, String receipt) { }
