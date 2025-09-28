package org.example;


public class StripeGateway {

    public boolean chargeCard(StripeChargeRequest request) {
        if (request.getAmountInCents() <= 0 || request.getToken() == null) {
            return false;
        }
        System.out.println("Stripe charged: " + request.getAmountInCents() + " cents for " + request.getDescription());
        return true;
    }
}


class StripeChargeRequest {
    private final int amountInCents;
    private final String token;
    private final String description;

    public StripeChargeRequest(int amountInCents, String token, String description) {
        this.amountInCents = amountInCents;
        this.token = token;
        this.description = description;
    }


    public int getAmountInCents() { return amountInCents; }
    public String getToken() { return token; }
    public String getDescription() { return description; }
}