package edu.ntnu.prog2.controller;

import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.model.Transaction;
import edu.ntnu.prog2.service.Exchange;

import java.math.BigDecimal;

public class GameController {
  private final Player player;
  private final Exchange exchange;

  public GameController(Player player, Exchange exchange) {
    this.player = player;
    this.exchange = exchange;
  }

  public void buyStock(String symbol, BigDecimal quantity) {
    try {
      Transaction transaction = exchange.buy(symbol, quantity, player);
      transaction.commit(player);
    } catch (Exception e) {
      throw new RuntimeException("Buy failed: " + e.getMessage());
    }
  }

  public void sellShare(Share share) {
    try {
      Transaction transaction = exchange.sell(share, player);
      transaction.commit(player);
    } catch (Exception e) {
      throw new RuntimeException("Sell failed: " + e.getMessage());
    }
  }

  public void nextWeek() {
    exchange.advance();
  }
}
