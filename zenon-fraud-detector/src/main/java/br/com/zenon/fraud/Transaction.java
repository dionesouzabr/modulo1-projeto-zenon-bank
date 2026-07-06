package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Objects;

public record Transaction(int step,
                          TransactionType type,
                          BigDecimal amount,
                          TransactionCustomer customerOrig,
                          TransactionCustomer customerDest,
                          boolean isFraud,
                          boolean isFlaggedFraud) {

    public Transaction {
        Objects.requireNonNull(type);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(customerOrig);
        Objects.requireNonNull(customerDest);


       if(step <=0) throw new IllegalArgumentException("O step deve ser um valor positivo." + step);
       if(amount.signum() < 0) throw new IllegalArgumentException("O valor deve ser um valor positivo ou zero." + amount);
    }
}



