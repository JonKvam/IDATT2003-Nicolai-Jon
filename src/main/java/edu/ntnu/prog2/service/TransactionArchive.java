package edu.ntnu.prog2.service;

import edu.ntnu.prog2.model.Transaction;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;


public class TransactionArchive {

  public ArrayList<Transaction> transactions;

  public TransactionArchive() {
    this.transactions = new ArrayList<>();
  }

  public boolean addTransaction(Transaction transaction) {
    return transactions.add(transaction);
  }

  public boolean isEmpty() {
    return transactions.isEmpty();
  }

  public List<Transaction> getTransactions(int week) {
    return transactions.stream()
            .filter(transaction -> transaction.getWeek() == week)
            .collect(Collectors.toList());
  }

  public List<Purchase> getPurchases(int week) {
    return transactions.stream()
            .filter(transaction -> transaction instanceof Purchase)
            .filter(transaction -> transaction.getWeek() == week)
            .map(transaction -> (Purchase) transaction)
            .collect(Collectors.toList());
  }

  public List<Sale> getSales(int week) {
    return transactions.stream()
            .filter(transaction -> transaction instanceof Sale)
            .filter(transaction -> transaction.getWeek() == week)
            .map(transaction -> (Sale) transaction)
            .collect(Collectors.toList());
  }

  public int countDistinctWeeks() {
    return Math.toIntExact(transactions.stream()
            .map(Transaction::getWeek)
            .distinct()
            .count());
  }

}
