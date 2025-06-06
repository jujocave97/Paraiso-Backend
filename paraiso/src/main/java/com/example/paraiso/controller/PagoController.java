package com.example.paraiso.controller;

import com.example.paraiso.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pago")
public class PagoController {

    private final StripeService stripeService;

    public PagoController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> crearPago() throws StripeException {
        PaymentIntent paymentIntent = stripeService.createPaymentIntent(500, "eur"); // la cantidad en euros x 100
        Map<String, String> response = new HashMap<>();
        response.put("clientSecret", paymentIntent.getClientSecret());
        return ResponseEntity.ok(response);
    }
}
