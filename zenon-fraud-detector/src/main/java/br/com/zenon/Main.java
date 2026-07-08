package br.com.zenon;


import br.com.zenon.fraud.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Main {

    void main() {


        Transaction primeriaTransaction = new Transaction(
            742,
            TransactionType.CASH_IN,
            new BigDecimal("1000.0"),
            new TransactionCustomer(
                "C123456789",
                new BigDecimal("1000.0"),
                new BigDecimal("0.0")
            ),
            new TransactionCustomer(
                "C987654321",
                new BigDecimal("5000.0"),
                new BigDecimal("6000.0")
            ),
            true,
            false
        );

        Transaction segundaTransaction = new Transaction(
            743,
            TransactionType.CASH_OUT,
            new BigDecimal("850002.52"),
            new TransactionCustomer(
                "C1280323807",
                new BigDecimal("850002.52"),
                new BigDecimal("0.0")
            ),
            new TransactionCustomer(
                "C873221189",
                new BigDecimal("6510099.11"),
                new BigDecimal("7360101.63")
            ),
            true,
            false
        );

        IO.println("Primeira Transação: " + primeriaTransaction);
        IO.println("Segunda Transação: " + segundaTransaction);
        IO.println("----------------------------------");
        IO.println("                                  ");

        var transactionIngestor = new TransactionIngestor();
        List<Transaction> transactions = transactionIngestor.read("zenon-fraud-detector/data/transactions.csv");


//        var fraudAnalyzer = new FraudAnalyzer(transactions);
//
//        long countFrauds = fraudAnalyzer.countFrauds();
//        IO.println("1. Total de Fraudes: " + countFrauds);
//
//        List<BigDecimal> highestFraudAmounts = fraudAnalyzer.findHighestValueFraudsAmounts(3);
//        IO.println("2. Top 3 Fraudes de Maior Valor: ");
//        highestFraudAmounts.forEach(amount -> IO.println("- %.2f".formatted(amount)));
//
//        IO.println("3. Clientes Suspeitos: ");
//        List<String> suspectClients = fraudAnalyzer.findTopSuspiciousClients(5);
//        suspectClients.forEach(IO::println);
//
//        var totalPrejuizo = fraudAnalyzer.calculateTotalFraudLoss();
//        IO.println("4. Prejuízo Total: " + totalPrejuizo);
//
//        var fraudCountByType = fraudAnalyzer.countFraudsByType();
//        IO.println("5. Fraudes por Tipo: ");
//        fraudCountByType.forEach((type, count) -> IO.println("- %s: %d".formatted(type, count)));

//        IO.println("----------------------------------");
//        IO.println("                                  ");
//
//        List<Transaction> transactionsBadData = transactionIngestor.read("zenon-fraud-detector/data/paysim_with_bad_data.csv");
//
//        IO.println(transactionsBadData.size());
//
//        transactionsBadData.forEach(IO::println);

        IO.println("----------------------------------");
        IO.println("                                  ");

        TransactionRepository transactionRepository;

        transactionRepository = new TransactionListRepository(transactions);

        String notFoundOriginName = "C12345";

        transactionRepository.findByOriginName(notFoundOriginName)
                        .ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente " + notFoundOriginName));


        String existingOriginName = "C1868032458";

        long startTimeList = System.nanoTime();
        transactionRepository.findByOriginName(existingOriginName)
                .ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente " + existingOriginName));
        long endTimeList = System.nanoTime();
        IO.println("Tempo de busca - List (ms): " + (endTimeList - startTimeList) / 1_000_000.0);

        transactionRepository = new TransactionMapRepository(transactions);


        startTimeList = System.nanoTime();
        transactionRepository.findByOriginName(existingOriginName)
                .ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente " + existingOriginName));
        endTimeList = System.nanoTime();
        IO.println("Tempo de busca - Map (ms): " + (endTimeList - startTimeList) / 1_000_000.0);


    }
}
