package org.example;


public class ECommerceClient {
    private final PaymentProcessor processor;

    public ECommerceClient(PaymentProcessor processor) {
        this.processor = processor;
    }

    public void placeOrder(double amount, String cardNumber) {
        System.out.println("Client: Attempting to process $" + amount + " payment...");
        boolean success = processor.processPayment(amount, cardNumber);
        if (success) {
            System.out.println("Client: Payment successful! Order placed.");
        } else {
            System.out.println("Client: Payment failed.");
        }
    }


    public static void main(String[] args) {
        StripeGateway stripe = new StripeGateway();
        PaymentProcessor adapter = new StripeAdapter(stripe);
        ECommerceClient client = new ECommerceClient(adapter);

        client.placeOrder(19.99, "1234");
//        client.placeOrder(-5.0, "1234");
    }
}