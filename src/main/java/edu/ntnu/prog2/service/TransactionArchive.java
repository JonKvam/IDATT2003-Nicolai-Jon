package edu.ntnu.prog2.service;

import edu.ntnu.prog2.model.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing an archive of transactions
 *
 * <p>The class consists of a constructor and methods to access and handle historical
 * information and statistics regarding transactions. </p>
 *
 * @author Jon
 */
public class TransactionArchive {

  public ArrayList<Transaction> transactions;

  /**
   * Constructs transactionArchive as an ArrayList.
   */
  public TransactionArchive() {
    this.transactions = new ArrayList<>();
  }

  /**
   * Adds a transaction to the archive.
   *
   * @param transaction transaction to be added
   * @return true if transaction was successfully added
   */
  public boolean addTransaction(Transaction transaction) {
    return transactions.add(transaction);
  }

  /**
   * Checks if transaction-archive is empty.
   *
   * @return true if archive is empty, false otherwise
   */
  public boolean isEmpty() {
    return transactions.isEmpty();
  }

  /**
   * Collects all transactions in a specific week to a list.
   *
   * @param week specific week to search for transactions
   * @return all transactions in specific week as a List
   */
  public List<Transaction> getTransactions(int week) {
    return transactions.stream()
            .filter(transaction -> transaction.getWeek() == week)
            .collect(Collectors.toList());
  }

  /**
   * Collects all purchases in a specific week to a list.
   *
   * @param week specific week to search for purchases
   * @return all purchases in specific week as a List
   */
  public List<Purchase> getPurchases(int week) {
    return transactions.stream()
            .filter(transaction -> transaction instanceof Purchase)
            .filter(transaction -> transaction.getWeek() == week)
            .map(transaction -> (Purchase) transaction)
            .collect(Collectors.toList());
  }

  /**
   * Collects all sales in a specific week to a list.
   *
   * @param week specific week to search for sales
   * @return all sales in specific week as a List
   */
  public List<Sale> getSales(int week) {
    return transactions.stream()
            .filter(transaction -> transaction instanceof Sale)
            .filter(transaction -> transaction.getWeek() == week)
            .map(transaction -> (Sale) transaction)
            .collect(Collectors.toList());
  }

  /**
   * Checks how many distinct weeks transactions have occurred.
   *
   * @return amount of unique weeks with transactions as an integer
   */
  public int countDistinctWeeks() {
    return Math.toIntExact(transactions.stream()
            .map(Transaction::getWeek)
            .distinct()
            .count());
  }

}
