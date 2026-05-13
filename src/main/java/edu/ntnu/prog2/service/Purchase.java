package edu.ntnu.prog2.service;

import edu.ntnu.prog2.calculator.PurchaseCalculator;
import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.model.Transaction;

/**
 * Subclass extending Transaction to handle purchases.
 *
 * <p>The class commits purchases. </p>
 *
 * @author Nicolai
 */
public class Purchase extends Transaction {
  public Purchase(Share share, int week){
    super(share, week, new PurchaseCalculator(share));
  }
  
  /**
   * Constructor for purchase.
   *
   * @param share instance of share to be bought
   * @param week the week of which the purchase takes place
   */
  @Override
  public void commit(Player player) {
    validateCommit(player);
    player.withdrawMoney(calculator.calculateTotal());
    player.getPortfolio().addShare(getShare());
    player.getTransactionArchive().addTransaction(this);
  }
}
