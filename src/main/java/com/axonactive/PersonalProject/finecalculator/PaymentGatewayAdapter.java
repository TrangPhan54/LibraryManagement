//package com.axonactive.PersonalProject.finecalculator;
//
//public class PaymentGatewayAdapter implements PaymentGateway{
//    private FineCalculator fineCalculator;
//
//    public PaymentGatewayAdapter(FineCalculator fineCalculator) {
//        this.fineCalculator = fineCalculator;
//    }
//
//    @Override
//    public void processPayment(double numberOfDays) {
//
//        double fineAmount = fineCalculator.calculateFine(numberOfDays);
//
//        // Process the payment using the adapted fine amount
//        System.out.println("Processing payment for overdue days: You return books late " + numberOfDays + " days, Fine amount: $" + fineAmount);
//        // Additional code for processing the payment, e.g., updating the payment status in the library system
//    }
//
//}
