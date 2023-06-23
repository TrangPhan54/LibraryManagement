package com.axonactive.PersonalProject.finecalculator;

public class PaymentGatewayAdapter implements PaymentGateway {
    private final FineCalculator fineCalculator;

    public PaymentGatewayAdapter(FineCalculator fineCalculator) {
        this.fineCalculator = fineCalculator;
    }

    @Override
    public Double processPayment(Long numberOfDays) {
        return fineCalculator.calculatingFineFee(numberOfDays);
    }

    @Override
    public Double processFineBookLost(Double importPrice) {
        return fineCalculator.calculatingFeeBookLost(importPrice);
    }

}
