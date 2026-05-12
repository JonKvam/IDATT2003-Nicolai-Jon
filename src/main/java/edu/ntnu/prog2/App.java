package edu.ntnu.prog2;

import edu.ntnu.prog2.controller.GameController;
import edu.ntnu.prog2.controller.WelcomeController;
import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Stock;
import edu.ntnu.prog2.service.Exchange;
import edu.ntnu.prog2.view.MainView;
import edu.ntnu.prog2.view.WelcomeView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.math.BigDecimal;
import java.util.List;
import javafx.stage.Stage;

public class App extends Application {
  private Stage stage;
  private static Scene scene;

  private WelcomeView welcomeView;
  private MainView mainView;

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
    welcomeView = new WelcomeView(stage);
    new WelcomeController(welcomeView, this);
    scene.setRoot(welcomeView);
  }

  public void switchToMainView(GameController controller, Player player, Exchange exchange) {
    mainView = new MainView(controller, player, exchange);
    scene.setRoot(mainView);
  }

  public void showPopupMessage(String messageToShow, Alert.AlertType type) {
    Alert alert = new Alert(type);
    alert.setContentText(messageToShow);
    alert.showAndWait();
  }
}
