package edu.ntnu.prog2.view;

import edu.ntnu.prog2.App;
import edu.ntnu.prog2.controller.GameController;
import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Stock;
import edu.ntnu.prog2.observer.Observer;
import edu.ntnu.prog2.service.Exchange;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.math.BigDecimal;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


public class MainView extends VBox implements Observer {
  private final GameController controller;
  private final Player player;
  private final Exchange exchange;
  private final App app;
  private EventHandler<ActionEvent> searchAction;
  private Label moneyLabel;
  private Label weekLabel;
  private ListView<Stock> bestPerformingStockList;
  private ListView<Stock> worstPerformingStockList;
  private ListView<Stock> stockList;
  private TextField searchField;
  private ComboBox<String> filtering;


  public MainView(App app, GameController controller, Player player, Exchange exchange) {
    this.app = app;
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

    Button exchangeBtn =  new Button(" Stock Exchange");
    Button transactionBtn =  new Button(" Stock Transaction");
    Button portfolioBtn =  new Button(" Stock Portfolio");

    portfolioBtn.setOnAction(e -> {
      app.switchToPortfolioView(controller, player, exchange);
    });

    HBox topMenu = new HBox(20);

    topMenu.getChildren().addAll(exchangeBtn, transactionBtn, portfolioBtn);

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
    stockList.setCellFactory(listView -> new javafx.scene.control.ListCell<>() {
      private final Button seeMoreBtn = new Button("See more");

      @Override
      protected void updateItem(Stock stock, boolean empty) {
        super.updateItem(stock, empty);
        if (empty || stock == null) {
          setText(null);
          setGraphic(null);
        } else {
          seeMoreBtn.setOnAction(event -> {
            app.switchToStockView(stock, controller, player, exchange);
          });
          HBox cellBox = new HBox(20);
          Label symbolLabel = new Label(stock.getSymbol());
          symbolLabel.setPrefWidth(80);
          Label companyLabel = new Label(stock.getCompany());
          companyLabel.setPrefWidth(250);
          Label priceLabel = new Label(stock.getSalesPrice().toString());
          priceLabel.setPrefWidth(100);

          String changeText;
          if (stock.getLatestPriceChange().compareTo(BigDecimal.ZERO) > 0) {
            changeText = "+ " + stock.getLatestPriceChange();
          } else if (stock.getLatestPriceChange().compareTo(BigDecimal.ZERO) < 0) {
            changeText = " " + stock.getLatestPriceChange();
          } else {
            changeText = "0.00";
          }
          Label changeTextLabel = new Label(changeText);
          changeTextLabel.setPrefWidth(100);
          Region spacer1 = new Region();
          Region spacer2 = new Region();
          Region spacer3 = new Region();
          Region spacer4 = new Region();
          HBox.setHgrow(spacer1, Priority.ALWAYS);
          HBox.setHgrow(spacer2, Priority.ALWAYS);
          HBox.setHgrow(spacer3, Priority.ALWAYS);
          HBox.setHgrow(spacer4, Priority.ALWAYS);
          cellBox.getChildren().addAll(symbolLabel, spacer1, companyLabel, spacer2, priceLabel, spacer3, changeTextLabel, spacer4, seeMoreBtn);
          setGraphic(cellBox);
        }
      }
    });
    stockList.setPrefWidth(500);
    stockList.getItems().setAll(exchange.getAllStocks());

    Button nextWeekBtn = new Button("Next Week");
    nextWeekBtn.setOnAction(e -> controller.nextWeek());

    Button buyBtn = new Button("Buy selected");
    buyBtn.setOnAction(e -> {
      Stock selectedStock = stockList.getSelectionModel().getSelectedItem();

      if (selectedStock != null) {
        controller.buyStock(selectedStock.getSymbol(), BigDecimal.ONE);
      }
      update();
    });

    getChildren().addAll(
            topMenu,
            weekLabel,
            moneyLabel,
            searchField,
            filtering,
            stockList,
            performanceBox,
            buyBtn,
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
