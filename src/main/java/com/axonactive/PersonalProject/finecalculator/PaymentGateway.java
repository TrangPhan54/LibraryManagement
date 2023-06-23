package com.axonactive.PersonalProject.finecalculator;

public interface PaymentGateway {
    Double processPayment(Long amount);
    Double processFineBookLost (Double importPrice);
}