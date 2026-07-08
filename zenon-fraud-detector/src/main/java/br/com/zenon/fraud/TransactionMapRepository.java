package br.com.zenon.fraud;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionMapRepository implements TransactionRepository {

    private Map<String, Transaction> transactionByOriginName;

    public TransactionMapRepository(List<Transaction> transactions) {
        Objects.requireNonNull(transactions);
        this.transactionByOriginName =
                transactions
                        .stream()
                        .collect(Collectors.toMap(transaction -> transaction.customerOrig().name(),
                                Function.identity()) // transaction -> transaction
                        );
    }

    @Override
    public Optional<Transaction> findByOriginName(String customerName) {
        return Optional.ofNullable(transactionByOriginName.get(customerName));
    }
}
