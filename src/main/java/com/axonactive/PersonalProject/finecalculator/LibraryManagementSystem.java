//package com.axonactive.PersonalProject.finecalculator;
//
//import java.util.Scanner;
//
//public class LibraryManagementSystem {
//    public static void main(String[] args) {
//        // Create an instance of FineCalculator and PaymentGatewayAdapter
//        FineCalculator fineCalculator = new FineCalculator();
//        PaymentGateway paymentGateway = new PaymentGatewayAdapter(fineCalculator);
//
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Please enter the number of overdue days: ");
//        // Simulate a payment request for an overdue book with a specific amount
//        double paymentAmount = sc.nextDouble(); // This could be the amount entered by the user or retrieved from the library system
//
//        // Process the payment using the adapted PaymentGatewayAdapter
//        paymentGateway.processPayment(paymentAmount);
////        payPalPaymentGateway.processPayment(100);
//
//    }
//}
