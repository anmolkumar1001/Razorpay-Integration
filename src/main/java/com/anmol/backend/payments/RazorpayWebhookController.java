package com.anmol.backend.payments;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks/razorpay")
public class RazorpayWebhookController {

    @Value("${razorpay.webhook_secret}")
    private String webhookSecret;

    @PostMapping
    public ResponseEntity<Void> onEvent(HttpServletRequest req, @RequestHeader("X-Razorpay-Signature") String signature) throws Exception {

    }
}
