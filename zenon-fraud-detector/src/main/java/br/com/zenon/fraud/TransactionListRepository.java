package br.com.zenon.fraud;

import java.util.List;
import java.util.Optional;

public class TransactionListRepository implements TransactionRepository {

    private final List<Transaction> transactions;

    public TransactionListRepository(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public Optional<Transaction> findByOriginName(String customerName) {
        return transactions.stream()
                .filter(transaction -> transaction.customerOrig().name().equals(customerName))
                .findFirst();
    }
}
