package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class FraudAnalyzer {

    public int isFraudOnArchive(List<Transaction> transactions) {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .toList()
                .size();
    }

    public List<Transaction> valueMaxFraud(List<Transaction> transactions) {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .sorted((t1, t2) -> t2.amount().compareTo(t1.amount()))
                .limit(3)
                .toList();
    }

    public List<String> validateSuspectClient(List<Transaction> transactions) {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .map(Transaction::customerOrig)
                .map(TransactionCustomer::name)
                .distinct()
                .toList();
    }

    public BigDecimal calculaPrejuizoTotal(List<Transaction> transactions) {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public HashMap<TransactionType, Integer> countFraudType(List<Transaction> transactions) {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .map(Transaction::type)
                .collect(HashMap::new, (map, type) -> map.put(type, map.getOrDefault(type, 0) + 1), HashMap::putAll);
    }

}

