package edu.ntnu.prog2;

import edu.ntnu.prog2.controller.GameController;
import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Stock;
import edu.ntnu.prog2.service.Exchange;
import edu.ntnu.prog2.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.math.BigDecimal;
import java.util.List;

public class App extends Application {
  @Override
  public void start(Stage stage) {
    Player player = new Player("Test", new BigDecimal("10000"), new BigDecimal("10000"));

    List<Stock> stocks = List.of(
            new Stock("AAPL", "Apple", new BigDecimal("100"))
    );

    Exchange exchange = new Exchange("TestExchange", stocks);

    GameController controller = new GameController(player, exchange);

    MainView view = new MainView(controller, player, exchange);

    Scene scene = new Scene(view, 400, 400);
    stage.setScene(scene);
    stage.setTitle("Millions");
    stage.setMaximized(true);
    stage.show();
  }
}
