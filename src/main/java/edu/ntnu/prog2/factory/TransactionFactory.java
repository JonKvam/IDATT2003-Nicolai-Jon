package edu.ntnu.prog2.factory;

import edu.ntnu.prog2.calculator.PurchaseCalculator;
import edu.ntnu.prog2.calculator.SaleCalculator;
import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.model.Transaction;
import edu.ntnu.prog2.service.Purchase;
import edu.ntnu.prog2.service.Sale;

public class TransactionFactory {

  public enum TransactionType {
    PURCHASE,
    SALE
  }

  public static Transaction createTransaction(TransactionType type, Share share, int week) {
    switch (type) {
      case PURCHASE:
        return new Purchase(share, week);
      case SALE:
        return new Sale(share, week);
      default:
        throw new IllegalArgumentException("Invalid TransactionType");
    }
  }
}
