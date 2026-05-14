package edu.ntnu.prog2.controller;

import edu.ntnu.prog2.App;
import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Stock;
import edu.ntnu.prog2.service.Exchange;
import edu.ntnu.prog2.view.StockView;
import javafx.scene.control.Alert;

import java.math.BigDecimal;

public class StockController {
  private StockView stockView;
  private Stock stock;
  private Player player;
  private Exchange exchange;
  private GameController gameController;
  private App app;

  public StockController(
          StockView view,
          Stock stock,
          Player player,
          Exchange exchange,
          GameController gameController,
          App app) {
    this.stockView = view;
    this.stock = stock;
    this.player = player;
    this.exchange = exchange;
    this.gameController = gameController;
    this.app = app;

    setupView();
    setupActions();
  }

  private void setupView() {
    stockView.setStockInfo(stock);
  }

  private void setupActions() {
    stockView.getBuyBtn().setOnAction(event -> {
      try {
        BigDecimal quantity = new BigDecimal(stockView.getQuantityTextField().getText());
        gameController.buyStock(stock.getSymbol(), quantity);
        app.showPopupMessage("Purchase successful", Alert.AlertType.INFORMATION);
      } catch (Exception ex) {
        app.showPopupMessage("There was an error with the purchase", Alert.AlertType.ERROR);
      }
    });

    stockView.getBackBtn().setOnAction(event -> {
      app.switchToMainView(gameController, player, exchange);
    });
  }
}
