package edu.ntnu.prog2.view;

import edu.ntnu.prog2.model.Stock;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StockView extends VBox {
  private final Label symbolLabel;
  private final Label companyLabel;
  private final Label currentPriceLabel;
  private final Label highestPriceLabel;
  private final Label lowestPriceLabel;
  private final Label buyStock;
  private final ListView<String> priceHistoryList;
  private final TextField quantityTextField;
  private final Button buyBtn;
  private final Button backBtn;
  private final Button increaseBtn;
  private final Button decreaseBtn;
  private final Button maxBtn;
  private final Button minBtn;

  public StockView() {
    setSpacing(10);
    setPadding(new Insets(30));

    setAlignment(Pos.TOP_CENTER);

    symbolLabel = new Label();
    companyLabel = new Label();
    currentPriceLabel = new Label();
    highestPriceLabel = new Label();
    lowestPriceLabel = new Label();
    priceHistoryList = new ListView<>();
    priceHistoryList.setPrefWidth(300);
    buyStock = new Label();

    increaseBtn = new Button("+");
    decreaseBtn = new Button("-");
    maxBtn = new Button("Max");
    minBtn = new Button("Min");
    quantityTextField = new TextField();
    quantityTextField.setPromptText("Enter stock quantity");

    HBox quantityBox = new HBox(10);
    quantityBox.setAlignment(Pos.CENTER);
    quantityBox.getChildren().addAll(minBtn, decreaseBtn, quantityTextField, increaseBtn, maxBtn);


    buyBtn = new Button("Buy shares");
    backBtn = new Button("Back");

    getChildren().addAll(
            symbolLabel,
            companyLabel,
            currentPriceLabel,
            highestPriceLabel,
            lowestPriceLabel,
            priceHistoryList,
            buyStock,
            quantityBox,
            buyBtn,
            backBtn
    );
  }

  public void setStockInfo(Stock stock) {
    symbolLabel.setText("Symbol: " + stock.getSymbol());
    companyLabel.setText("Company: " + stock.getCompany());
    currentPriceLabel.setText("Current price: $" + stock.getSalesPrice());
    highestPriceLabel.setText("Highest price: $" + stock.getHighestPrice());
    lowestPriceLabel.setText("Lowest price: $" + stock.getLowestPrice());
    buyStock.setText("Buy shares in this stock");
    priceHistoryList.getItems().clear();
    for (int i = 0; i < stock.getHistoricalPrices().size(); i++) {
      priceHistoryList.getItems().add("Week " + (i + 1) + ": $" + stock.getHistoricalPrices().get(i));
    }
  }

  public TextField getQuantityTextField() {
    return quantityTextField;
  }

  public Button getBuyBtn() {
    return buyBtn;
  }

  public Button getBackBtn() {
    return backBtn;
  }

  public Button getIncreaseBtn() {
    return increaseBtn;
  }

  public Button getDecreaseBtn() {
    return decreaseBtn;
  }

  public Button getMaxBtn() {
    return maxBtn;
  }

  public Button getMinBtn() {
    return minBtn;
  }
}
