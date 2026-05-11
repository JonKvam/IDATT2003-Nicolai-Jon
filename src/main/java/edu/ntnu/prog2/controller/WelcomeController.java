package edu.ntnu.prog2.controller;

import edu.ntnu.prog2.App;
import edu.ntnu.prog2.io.StockFileReader;
import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Stock;
import edu.ntnu.prog2.service.Exchange;
import edu.ntnu.prog2.view.WelcomeView;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class WelcomeController {

  private final WelcomeView welcomeView;
  private final App app;

  public WelcomeController(
          WelcomeView welcomeView,
          App app
  ) {

    this.welcomeView = welcomeView;
    this.app = app;

    setupViewActions();
  }

  private void setupViewActions() {

    welcomeView.setStartGameBtnOnAction(e -> {

      try {

        String playerName = welcomeView.getPlayerName();
        String startCapitalText = welcomeView.getStartCapital();

        if (welcomeView.getSelectedFile() == null) {
          app.showPopupMessage("Please select a CSV file or choose the trading file", AlertType.ERROR);
          return;
        }

        BigDecimal startCapital = new BigDecimal(startCapitalText);

        List<Stock> stocks = StockFileReader.readStocksFromFile(
                welcomeView.getSelectedFile().getAbsolutePath()
        );

        Player player = new Player(
                playerName,
                startCapital,
                startCapital
        );

        Exchange exchange = new Exchange("Stock Exchange", stocks);

        GameController gameController = new GameController(player, exchange);

        app.switchToMainView(
                gameController,
                player,
                exchange
        );

      } catch (NumberFormatException er) {

        app.showPopupMessage(
                "Start capital must be a valid number",
                AlertType.ERROR
        );

      } catch (IOException er) {

        app.showPopupMessage(
                "Could not read CSV file",
                AlertType.ERROR
        );
      }
    });
  }
}