package br.com.zenon;


import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionCustomer;
import br.com.zenon.fraud.TransactionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TransactionIngestor {

    public List<Transaction> read(String fileName) {
        Path path = Path.of(fileName);
        try {
            List<String> lines = Files.readAllLines(path);
            return lines.stream()
                    .skip(1)
                    .limit(1000)
                    .map(this::parseTransaction)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Optional<Transaction> parseTransaction(String line) {
        try {
            String[] chunks = line.split(",");

            int step = Integer.parseInt(chunks[0]);
            TransactionType transactionType = TransactionType.valueOf(chunks[1]);

            if(chunks[2] == null || chunks[2].isEmpty()) throw new IllegalArgumentException("O valor de amount não pode ser nulo nem vazio.");
            BigDecimal amount = new BigDecimal(chunks[2]);

            TransactionCustomer customerOrig = new TransactionCustomer(chunks[3], new BigDecimal(chunks[4]), new BigDecimal(chunks[5]));
            TransactionCustomer customerDest = new TransactionCustomer(chunks[6], new BigDecimal(chunks[7]), new BigDecimal(chunks[8]));

            if(!Objects.equals(chunks[9], "0") && !Objects.equals(chunks[9], "1")) throw new IllegalArgumentException("O valor de isFraud deve ser '0' ou '1'." + chunks[9]);
            boolean isFraud = "1".equals(chunks[9]);

            if(!Objects.equals(chunks[10], "0") && !Objects.equals(chunks[10], "1")) throw new IllegalArgumentException("O valor de isFlaggedFraud deve ser '0' ou '1'." + chunks[10]);
            boolean isFlaggedFraud = "1".equals(chunks[10]);

            return Optional.of(new Transaction(step, transactionType, amount, customerOrig, customerDest, isFraud, isFlaggedFraud));
        } catch (Exception e) {
            System.err.println("Error ao fazer parse: " + line + " | " + e.getMessage());
        }
        return Optional.empty();
    }
}
