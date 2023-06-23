package com.axonactive.PersonalProject.finecalculator;

public class FineCalculator {
    private static final Double BASE_FEE = 2.0;
    private static final Double FINE_PER_DAY = 0.5;
    private static final Double BONUS_FACTOR = 1.02;
    private static final Double PENALTY_FACTOR = Math.pow(BONUS_FACTOR, 2);
    private static final Double LIMITATION_OVERDUE_DAY = 7.0;
    private static final Double FACTOR_LOST_BOOK = 1.5;


    public Double calculatingFineFee(Long overdueDays) {
        double fine = 0;
        if (overdueDays <= LIMITATION_OVERDUE_DAY) {
            fine = BASE_FEE + FINE_PER_DAY * overdueDays;
        } else {
            fine *= PENALTY_FACTOR;
        }
        return fine;
    }

    public Double calculatingFeeBookLost(Double importPrice) {
        return importPrice * FACTOR_LOST_BOOK;
    }
}
