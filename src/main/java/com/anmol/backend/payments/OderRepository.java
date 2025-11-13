package com.anmol.backend.payments;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByRazorpayOrderId(String orderId);
}
