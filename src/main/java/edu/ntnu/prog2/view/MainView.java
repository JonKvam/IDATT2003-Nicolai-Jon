package edu.ntnu.prog2.view;

import edu.ntnu.prog2.controller.GameController;
import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.model.Stock;
import edu.ntnu.prog2.observer.Observer;
import edu.ntnu.prog2.service.Exchange;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;

public class MainView extends VBox implements Observer {
  private final GameController controller;
  private final Player player;
  private final Exchange exchange;
  private EventHandler<ActionEvent> searchAction;
  private Label moneyLabel;
  private Label netWorthLabel;
  private Label weekLabel;
  private ListView<Share> portfolioList;
  private ListView<Stock> stockList;
  private TextField searchField;


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
    stockList = new ListView<>();

    searchField = new TextField();
    searchField.setPromptText("search for stocks");
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.isBlank()) {
        stockList.getItems().setAll(exchange.getAllStocks());
      } else {
        stockList.getItems().setAll(exchange.findStocks(newValue));
      }
    });


    stockList.getItems().setAll(exchange.getAllStocks());

    Button nextWeekBtn = new Button("Next Week");
    nextWeekBtn.setOnAction(e -> controller.nextWeek());

    Button buyBtn = new Button("Buy selected");

    Button sellBtn = new Button("Sell selected");

    getChildren().addAll(
            weekLabel,
            moneyLabel,
            netWorthLabel,
            searchField,
            stockList,
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

  private void triggerSearch() {
    if (searchAction != null) {
      searchAction.handle(new ActionEvent(searchField, searchField));
    }
  }

  @Override
  public void update() {
    moneyLabel.setText("Money: " + player.getMoney());
    netWorthLabel.setText("Net worth: " + player.getNetWorth());
    weekLabel.setText("Week: " + exchange.getWeek());

    portfolioList.getItems().setAll(player.getPortfolio().getShares());
  }
}
