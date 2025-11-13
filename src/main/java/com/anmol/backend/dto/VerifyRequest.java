package com.anmol.backend.dto;

public record VerifyRequest(String razorpay_order_id, String razorpay_payment_id, String razorpay_signature) { }
