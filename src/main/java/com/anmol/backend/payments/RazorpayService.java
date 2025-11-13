package com.anmol.backend.payments;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RazorpayService {

    private final RazorpayClient client;
    private final String keySecret;

    public RazorpayService (
            @Value("${razorpay.key_id}")
            String keyId,
            @Value("${razorpay.key_secret}")
            String keySecret
    ) throws RazorpayException {
        this.client = new RazorpayClient(keyId, keySecret);
        this.keySecret = keySecret;
    }

    public Order createOrder(long rupees, String currency, String receipt) throws RazorpayException {

        int amountPaise = Math.toIntExact(rupees * 100);

        JSONObject req = new JSONObject();
        req.put("amount", amountPaise);
        req.put("currency", (currency == null || currency.isBlank()) ? "INR" : currency);
        req.put("receipt", (receipt == null || receipt.isBlank()) ? java.util.UUID.randomUUID().toString() : receipt);

        return client.orders.create(req);
    }

    public boolean verifyPayment(String orderId, String paymentId, String signature) throws RazorpayException {

        JSONObject data = new JSONObject();
        data.put("razorpay_order_id", orderId);
        data.put("razorpay_payment_id", paymentId);
        data.put("razorpay_signature", signature);

        return Utils.verifyPaymentSignature(data, keySecret);
    }
}
