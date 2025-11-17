package com.anmol.backend.payments;

import com.razorpay.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;

@RestController
@RequestMapping("/api/webhooks/razorpay")
public class RazorpayWebhookController {

    @Value("${razorpay.webhook_secret}")
    private String webhookSecret;

    @PostMapping
    public ResponseEntity<Void> onEvent(HttpServletRequest req, @RequestHeader("X-Razorpay-Signature") String signature) throws Exception {

        // Read RAW body exactly as received
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = req.getReader()) {
            br.lines().forEach(sb::append);
        }

        String rawBody= sb.toString();

        // Verify against raw body
        if(!Utils.verifyWebhookSignature(rawBody, signature, webhookSecret)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // Parse AFTER verification
        JSONObject payload = new JSONObject(rawBody);
        String event = payload.optString("event", "");

        // TODO: handle events (payment.captured, payment.failed, order.paid) + idempotency

        return ResponseEntity.ok().build();
    }
}
