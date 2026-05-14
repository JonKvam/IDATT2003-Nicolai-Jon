package edu.ntnu.prog2;

import edu.ntnu.prog2.controller.GameController;
import edu.ntnu.prog2.controller.PortfolioController;
import edu.ntnu.prog2.controller.StockController;
import edu.ntnu.prog2.controller.WelcomeController;
import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Stock;
import edu.ntnu.prog2.service.Exchange;
import edu.ntnu.prog2.view.MainView;
import edu.ntnu.prog2.view.PortfolioView;
import edu.ntnu.prog2.view.StockView;
import edu.ntnu.prog2.view.WelcomeView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
  private Stage stage;
  private static Scene scene;

  @Override
  public void start(Stage stage) {

    this.stage = stage;
    scene = new Scene(new VBox(), 600, 450);
    scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

    switchToWelcome();

    stage.setTitle("Millions");
    stage.setScene(scene);
    stage.setMaximized(true);
    stage.show();
  }

  public void switchToWelcome() {
    WelcomeView welcomeView = new WelcomeView(stage);
    new WelcomeController(welcomeView, this);
    scene.setRoot(welcomeView);
  }

  public void switchToMainView(GameController controller, Player player, Exchange exchange) {
    MainView mainView = new MainView(this, controller, player, exchange);
    scene.setRoot(mainView);
  }

  public void switchToPortfolioView(GameController controller, Player player, Exchange exchange) {
    PortfolioView portfolioView = new PortfolioView();
    new PortfolioController(portfolioView, player, exchange, controller, this);
    scene.setRoot(portfolioView);
  }

  public void switchToStockView(Stock stock, GameController controller, Player player, Exchange exchange) {
    StockView stockView = new StockView();
    new StockController(stockView, stock, player, exchange, controller, this);
    scene.setRoot(stockView);
  }

  public void showPopupMessage(String messageToShow, Alert.AlertType type) {
    Alert alert = new Alert(type);
    alert.setContentText(messageToShow);
    alert.showAndWait();
  }
}
