package edu.ntnu.prog2.view;

import edu.ntnu.prog2.controller.GameController;
import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.model.Stock;
import edu.ntnu.prog2.observer.Observer;
import edu.ntnu.prog2.service.Exchange;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.util.List;

public class MainView extends VBox implements Observer {
  private final GameController controller;
  private final Player player;
  private final Exchange exchange;
  private EventHandler<ActionEvent> searchAction;
  private Label moneyLabel;
  private Label netWorthLabel;
  private Label weekLabel;
  private ListView<Stock> bestPerformingStockList;
  private ListView<Stock> worstPerformingStockList;
  private ListView<Stock> stockList;
  private TextField searchField;
  private ComboBox<String> filtering;


  public MainView(GameController controller, Player player, Exchange exchange) {
    this.controller = controller;
    this.player = player;
    this.exchange = exchange;

    setupUserInterface();
    registerObservers();
    update();
  }

  private void setupUserInterface() {
    setSpacing(10);
    setPadding(new Insets(30));

    moneyLabel = new Label();
    weekLabel = new Label();


    Label best = new Label("Best Performing Stocks:");
    best.getStyleClass().add("p");

    bestPerformingStockList = new ListView<>();
    bestPerformingStockList.setPrefWidth(500);

    VBox bestBox = new VBox(10);
    bestBox.setAlignment(Pos.TOP_CENTER);
    bestBox.getChildren().addAll(best, bestPerformingStockList);

    Label worst = new Label("Worst Performing Stocks");
    worst.getStyleClass().add("p");

    worstPerformingStockList = new ListView<>();
    worstPerformingStockList.setPrefWidth(500);

    VBox worstBox = new VBox(10);
    worstBox.setAlignment(Pos.TOP_CENTER);
    worstBox.getChildren().addAll(worst, worstPerformingStockList);

    HBox performanceBox = new HBox(20);
    performanceBox.getChildren().addAll(bestBox, worstBox);


    searchField = new TextField();
    searchField.setPromptText("search for stocks");
    searchField.getStyleClass().add("input-field");
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      updateStockList();
    });

    filtering = new ComboBox<>();
    filtering.setPromptText("Filter stocks");
    filtering.getItems().addAll("Alphabetical", "Highest price", "Lowest price");
    filtering.setOnAction(event -> updateStockList());
    filtering.setValue("Alphabetical");

    stockList = new ListView<>();
    stockList.getItems().setAll(exchange.getAllStocks());

    Button nextWeekBtn = new Button("Next Week");
    nextWeekBtn.setOnAction(e -> controller.nextWeek());

    Button buyBtn = new Button("Buy selected");

    Button sellBtn = new Button("Sell selected");

    getChildren().addAll(
            weekLabel,
            moneyLabel,
            searchField,
            filtering,
            stockList,
            performanceBox,
            buyBtn,
            sellBtn,
            nextWeekBtn
    );
  }

  private void registerObservers() {
    player.addObserver(this);
    exchange.addObserver(this);
  }

  private void updateStockList() {
    String searchText = searchField.getText();
    List<Stock> stocks;
    if (searchText == null || searchText.isEmpty()) {
      stocks = exchange.getAllStocks();
    } else {
      stocks = exchange.findStocks(searchText);
    }

    if (filtering.getValue().equals("Alphabetical")) {
      stocks.sort((stock1, stock2) -> stock1.getSymbol().compareTo(stock2.getSymbol()));
    } else if (filtering.getValue().equals("Highest price")) {
      stocks.sort((stock1, stock2) -> stock2.getSalesPrice().compareTo(stock1.getSalesPrice()));
    } else if (filtering.getValue().equals("Lowest price")) {
      stocks.sort((stock1, stock2) -> stock1.getSalesPrice().compareTo(stock2.getSalesPrice()));
    }

    stockList.getItems().setAll(stocks);
  }

  @Override
  public void update() {
    moneyLabel.setText("Money: " + player.getMoney());
    weekLabel.setText("Week: " + exchange.getWeek());
    updateStockList();
    bestPerformingStockList.getItems().setAll(exchange.getGainers(9));
    worstPerformingStockList.getItems().setAll(exchange.getLosers(9));
  }
}
