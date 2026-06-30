package br.com.zenon.fraud;

public record Transaction(Long step,
                          EnumPaymentType type,
                          Double amount,
                          String nameOrig,
                          Double oldBalanceOrg,
                          double newBalanceOrig,
                          String nameDest,
                          double oldBalanceDest,
                          double newBalanceDest,
                          int isFraud,
                          int isFlaggedFraud) {
}
