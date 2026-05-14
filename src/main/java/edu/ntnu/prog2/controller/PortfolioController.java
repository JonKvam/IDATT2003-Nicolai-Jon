package edu.ntnu.prog2.controller;

import edu.ntnu.prog2.App;
import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.service.Exchange;
import edu.ntnu.prog2.view.PortfolioView;
import javafx.scene.control.Alert;

import java.math.BigDecimal;

public class PortfolioController {
  private final Player player;
  private final Exchange exchange;
  private final PortfolioView view;
  private final GameController gameController;
  private final App app;

  public PortfolioController(PortfolioView view, Player player, Exchange exchange, GameController gameController, App app) {
    this.view = view;
    this.player = player;
    this.exchange = exchange;
    this.gameController = gameController;
    this.app = app;

    setupView();
    setupActions();
  }

  private void setupView() {
    view.getPortfolioList().getItems().setAll(player.getPortfolio().getShares());
    view.getMoneyLabel().setText("Money: $" + player.getMoney());
    view.getNetWorthLabel().setText("Net Worth: $" + player.getNetWorth());
    view.getWeekLabel().setText("Week: " + exchange.getWeek());

  }

  private void setupActions() {
    view.getSellBtn().setOnAction(e -> {
      Share selectedShare = view.getPortfolioList().getSelectionModel().getSelectedItem();
      if (selectedShare == null) {
        return;
      }

      String quantityText = view.getSellQuantityField().getText();
      if  (quantityText == null || quantityText.isEmpty()) {
        return;
      }

      BigDecimal quantityToSell = new BigDecimal(quantityText);
      if (quantityToSell.compareTo(selectedShare.getQuantity()) > 0) {
        app.showPopupMessage("Cannot sell more share than you own", Alert.AlertType.ERROR);
        return;
      }
      if (quantityToSell.compareTo(selectedShare.getQuantity()) == 0) {
        gameController.sellShare(selectedShare);
      } else {
        BigDecimal remainingQuantity = selectedShare.getQuantity().subtract(quantityToSell);
        player.getPortfolio().removeShare(selectedShare);
        Share remainingShare = new Share(selectedShare.getStock(), remainingQuantity, selectedShare.getPurchasePrice());
        player.getPortfolio().addShare(remainingShare);
        BigDecimal sellValue = selectedShare.getStock().getSalesPrice().multiply(quantityToSell);
        player.addMoney(sellValue);
        view.getSellQuantityField().clear();
        refresh();
      }
      refresh();
    });

    view.getBackBtn().setOnAction(e -> {
      app.switchToMainView(gameController, player, exchange);
    });
  }

  private void refresh() {
    setupView();
    view.getPortfolioList().refresh();
  }
}
