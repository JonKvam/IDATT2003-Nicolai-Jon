package edu.ntnu.prog2;

import java.util.ArrayList;

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
    //TODO
  }

  public List<Purchase> getPurchases(int week) {
    //TODO
  }

  public List<Sale> getSales(int week) {
    //TODO
  }

  public int countDistinctWeeks() {
    //TODO
  }
}
