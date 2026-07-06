package br.com.zenon.fraud;

import java.math.BigDecimal;

public record TransactionCustomer(String name, BigDecimal oldBalance, BigDecimal newBalance) {

    public TransactionCustomer {
        if(oldBalance.signum() < 0) throw new IllegalArgumentException("O valor do saldo antigo deve ser um valor positivo ou zero." + oldBalance);
        if(newBalance.signum() < 0) throw new IllegalArgumentException("O valor do saldo novo deve ser um valor positivo ou zero." + newBalance);
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("O nome do cliente não pode ser nulo nem vazio.");
    }

}
