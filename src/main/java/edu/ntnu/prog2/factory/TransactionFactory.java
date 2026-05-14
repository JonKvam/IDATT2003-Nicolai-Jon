package edu.ntnu.prog2.factory;

import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.model.Transaction;
import edu.ntnu.prog2.service.Purchase;
import edu.ntnu.prog2.service.Sale;

/**
 * Factory class to create transactions.
 *
 * <p>Creates purchases and sales based on type of transaction.</p>
 *
 * @author Nicolai
 */
public class TransactionFactory {

  /**
   * Representation of type of transaction.
   */
  public enum TransactionType {
    PURCHASE,
    SALE
  }

  /**
   * Creates transaction based on transaction type.
   *
   * @param type type of transaction to be created
   * @param share share to be sold or purchased
   * @param week week when transaction takes place
   * @return purchase or sale based on type
   * @throws IllegalArgumentException if transaction type is not sale or purchase
   */
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
