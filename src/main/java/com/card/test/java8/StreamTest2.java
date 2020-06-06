package com.card.test.java8;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cuihp on 2020/5/16.
 */
public class StreamTest2 {

    Trader raoul = new Trader("Raoul","Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");

    List<Transaction> transactions = Arrays.asList(
      new Transaction(brian,2011,300),
      new Transaction(raoul,2012,1000),
      new Transaction(raoul,2011,400),
      new Transaction(mario,2012,710),
      new Transaction(mario,2012,700),
      new Transaction(alan,2012,950)
    );


    public static void main(String[] args) {
        StreamTest2 streamTest2 = new StreamTest2();
        streamTest2.transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach( (t) -> {
                        System.out.print(t.toString()+" ");
                });
        System.out.println();
        streamTest2.transactions.stream()
                .map((t) -> t.getTrader().getCity())
                .distinct()
                .forEach((t) -> {
                    System.out.print(t+"_");
                });
        System.out.println();
        streamTest2.transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach( (t) -> {
                    System.out.print(t.toString()+" ");
                });
        System.out.println();
        streamTest2.transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted(Comparator.comparing(s -> s))
                .forEach( (t) -> {
                    System.out.print(t +" ");
                });

        System.out.println();
        boolean milan = streamTest2.transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milan);
        System.out.println();
        Integer cambridge = streamTest2.transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);
        System.out.println(cambridge);

        streamTest2.transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach( (t) -> {
                    System.out.print(t +" ");
                });

    }


}





class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}


class Transaction{
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}