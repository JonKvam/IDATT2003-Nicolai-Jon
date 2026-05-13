package edu.ntnu.prog2.service;

import edu.ntnu.prog2.calculator.PurchaseCalculator;
import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.model.Transaction;

/**
 * Subclass extending Transaction to handle sales.
 *
 * <p>The class commits sales. </p>
 *
 * @author Nicolai
 */
public class Sale extends Transaction {

  /**
   * Constructor for sales.
   *
   * @param share share to be sold
   * @param week week when sale takes place
   */
  public Sale(Share share, int week) {
    super(share, week, new PurchaseCalculator(share));
  }

  /**
   * Validates and commits a sale.
   *
   * @param player player to commit sale
   */
  @Override
  public void commit(Player player) {
    validateCommit(player);
    setCommitted(true);
  }
}
