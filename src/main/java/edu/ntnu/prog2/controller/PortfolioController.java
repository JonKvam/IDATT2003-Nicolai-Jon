package edu.ntnu.prog2.controller;

import edu.ntnu.prog2.App;
import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.service.Exchange;
import edu.ntnu.prog2.view.PortfolioView;

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
  }

  private void setupActions() {
    view.getSellBtn().setOnAction(e -> {
      Share selectedShare = view.getPortfolioList().getSelectionModel().getSelectedItem();
      if (selectedShare != null) {
        gameController.sellShare(selectedShare);
        refresh();
      }
    });

    view.getBackBtn().setOnAction(e -> {
      app.switchToMainView(gameController, player, exchange);
    });
  }

  private void refresh() {
    view.getPortfolioList().getItems().setAll(player.getPortfolio().getShares());
    view.getPortfolioList().refresh();
  }
}
