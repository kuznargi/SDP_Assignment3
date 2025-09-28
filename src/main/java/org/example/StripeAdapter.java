package org.example;

import java.util.Objects;


public class StripeAdapter implements PaymentProcessor {
    private final StripeGateway stripeGateway;
    public StripeAdapter(StripeGateway stripeGateway) {
        Objects.requireNonNull(stripeGateway, "StripeGateway cannot be null");
        this.stripeGateway = stripeGateway;
    }

    @Override
    public boolean processPayment(double amount, String cardNumber) {
       if (!isValidAmount(amount)) {
            throw new IllegalArgumentException("Payment amount must be greater than 0: " + amount);
        }

        StripeChargeRequest request = createStripeRequest(amount, cardNumber);

       return stripeGateway.chargeCard(request);
    }
    private boolean isValidAmount(double amount) {
        return amount > 0;
    }

    private StripeChargeRequest createStripeRequest(double amount, String cardNumber) {
        int cents = (int) (amount * 100);
        String token = "tok_" + cardNumber;
        String description = "E-commerce payment for card ending in " + cardNumber;
        return new StripeChargeRequest(cents, token, description);
    }
}