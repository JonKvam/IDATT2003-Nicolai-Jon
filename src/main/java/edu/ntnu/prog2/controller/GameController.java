package edu.ntnu.prog2.controller;

import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.model.Transaction;
import edu.ntnu.prog2.service.Exchange;
import java.math.BigDecimal;

/**
 * Controls possible actions for player.
 *
 * <p>Consists of a constructor and methods to make actions such as transactions
 * and proceeding to next week.</p>
 *
 * @author Nicolai
 */
public class GameController {
  private final Player player;
  private final Exchange exchange;

  /**
   * Constructor for player and exchange.
   *
   * @param player player to commit actions
   * @param exchange exchange for which actions occur
   */
  public GameController(Player player, Exchange exchange) {
    this.player = player;
    this.exchange = exchange;
  }

  /**
   * Method to buy share as player.
   *
   * @param symbol symbol of stock/share
   * @param quantity amount of stocks to be bought
   */
  public void buyStock(String symbol, BigDecimal quantity) {
    try {
      Transaction transaction = exchange.buy(symbol, quantity, player);
      transaction.commit(player);
    } catch (Exception e) {
      throw new RuntimeException("Buy failed: " + e.getMessage());
    }
  }

  /**
   * Method to sell share as player.
   *
   * @param share share to be sold
   */
  public void sellShare(Share share) {
    try {
      Transaction transaction = exchange.sell(share, player);
      transaction.commit(player);
    } catch (Exception e) {
      throw new RuntimeException("Sell failed: " + e.getMessage());
    }
  }

  /**
   * Method to advance to next week.
   */
  public void nextWeek() {
    exchange.advance();
  }
}
