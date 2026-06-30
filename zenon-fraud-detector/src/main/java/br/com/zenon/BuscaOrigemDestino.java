package br.com.zenon;


import br.com.zenon.fraud.EnumPaymentType;
import br.com.zenon.fraud.Transaction;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class BuscaOrigemDestino {

    public static void main(String[] args) {
        // Redireciona System.out para UTF-8 para garantir saída correta no console
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Transaction primeriaTransaction = new Transaction(
            742L,
            EnumPaymentType.CASH_IN,
            1000.0,
            "C123456789",
            1000.0,
            0.0,
            "C987654321",
            5000.0,
            6000.0,
            1,
            0
        );

        Transaction segundaTransaction = new Transaction(
            743L,
            EnumPaymentType.CASH_OUT,
            850002.52,
            "C1280323807",
            850002.52,
            0.0,
            "C873221189",
            6510099.11,
            7360101.63,
            1,
            0
        );

        IO.println("Primeira Transação: " + primeriaTransaction);
        IO.println("Segunda Transação: " + segundaTransaction);
    }
}
