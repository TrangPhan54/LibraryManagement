//package com.axonactive.PersonalProject.finecalculator;
//
//public class FineCalculator {
//    public double calculateFine(double overdueDays) {
//        // Calculate the fine amount based on the overdue days using a specific algorithm
//        double baseFee = 2.0; // Base fee for overdue books
//        double finePerDay = 0.5; // Fine rate per day for overdue books
//        double fine = baseFee + finePerDay * overdueDays;
//        if (overdueDays > 7) {
//            double penaltyFactor = Math.pow(1.2, overdueDays - 7);
//            fine *= penaltyFactor;
//        }
//
//        return fine;
//    }
//}
