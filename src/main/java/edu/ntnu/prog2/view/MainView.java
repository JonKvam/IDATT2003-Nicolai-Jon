package edu.ntnu.prog2.view;

import edu.ntnu.prog2.controller.GameController;
import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.observer.Observer;
import edu.ntnu.prog2.service.Exchange;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;

public class MainView extends VBox implements Observer {
  private final GameController controller;
  private final Player player;
  private final Exchange exchange;

  private Label moneyLabel;
  private Label netWorthLabel;
  private Label weekLabel;
  private ListView<Share> portfolioList;


  public MainView(GameController controller, Player player, Exchange exchange) {
    this.controller = controller;
    this.player = player;
    this.exchange = exchange;

    setupUserInterface();
    registerObservers();
    update();
  }

  private void setupUserInterface() {
    moneyLabel = new Label();
    netWorthLabel = new Label();
    weekLabel = new Label();

    portfolioList = new ListView<>();

    Button nextWeekBtn = new Button("Next Week");
    nextWeekBtn.setOnAction(e -> controller.nextWeek());

    Button buyBtn = new Button("Buy AAPL (10)");
    buyBtn.setOnAction(e ->
            controller.buyStock("AAPL", new BigDecimal("10"))
    );

    Button sellBtn = new Button("Sell selected");
    sellBtn.setOnAction(e -> {
      Share selected = portfolioList.getSelectionModel().getSelectedItem();
      if (selected != null) {
        controller.sellShare(selected);
      }
    });

    getChildren().addAll(
            weekLabel,
            moneyLabel,
            netWorthLabel,
            portfolioList,
            buyBtn,
            sellBtn,
            nextWeekBtn
    );
  }

  private void registerObservers() {
    player.addObserver(this);
    exchange.addObserver(this);
  }

  @Override
  public void update() {
    moneyLabel.setText("Money: " + player.getMoney());
    netWorthLabel.setText("Net worth: " + player.getNetWorth());
    weekLabel.setText("Week: " + exchange.getWeek());

    portfolioList.getItems().setAll(player.getPortfolio().getShares());
  }
}
