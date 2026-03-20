package edu.ntnu.prog2;

import edu.ntnu.prog2.model.Transaction;
import edu.ntnu.prog2.Purchase;
import edu.ntnu.prog2.service.Sale;

import java.util.ArrayList;
import java.util.List;

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
