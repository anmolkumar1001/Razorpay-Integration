package com.anmol.backend.payments;

import com.anmol.backend.dto.CreateOrderRequest;
import com.anmol.backend.dto.CreateOrderResponse;
import com.anmol.backend.dto.VerifyRequest;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final RazorpayService ser;
    private final OrderRepository repo;

    public PaymentController(RazorpayService ser, OrderRepository repo) {
        this.ser = ser;
        this.repo = repo;
    }

    @PostMapping("/create-order")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest req) throws RazorpayException {

        Order order = ser.createOrder(req.amountRupees(), req.currency(), req.receipt());

        OrderEntity e = new OrderEntity();
        e.setRazorpayOrderId(order.get("id"));
        e.setAmountPaise(order.get("amount"));
        e.setCurrency(order.get("currency"));
        e.setStatus("created");
        repo.save(e);

        return ResponseEntity.ok(
                new CreateOrderResponse(order.get("id"), order.get("amount"), order.get("currency"))
        );
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody VerifyRequest req) throws RazorpayException {

        boolean ok = ser.verifyPayment(req.razorpay_order_id(), req.razorpay_payment_id(), req.razorpay_signature());

        if(!ok) {
            return ResponseEntity.badRequest().body("{\"error\":\"Invalid signature\"}");
        }

        repo.findByRazorpayOrderId(req.razorpay_order_id()).ifPresent(ord -> {
            ord.setStatus("paid");
            ord.setPaymentId(req.razorpay_payment_id());
            repo.save(ord);
        });

        return ResponseEntity.ok("{\"message\":\"Payment verified\"}");

    }
}
