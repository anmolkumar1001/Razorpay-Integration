package com.anmol.backend.payments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
public class PaymentConfigController {

    @Value("${razorpay.key_id}")
    private String razorpayKeyId;

    @GetMapping("/key")
    public String getKey() {
        return razorpayKeyId;  // Only public key
    }
}
