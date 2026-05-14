package edu.ntnu.prog2.view;

import edu.ntnu.prog2.model.Share;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PortfolioView extends VBox {
  private final ListView<Share> portfolioList;
  private Label moneyLabel;
  private Label weekLabel;
  private Label netWorthLabel;
  private Button sellBtn;
  private Button backBtn;
  private TextField sellQuantityField;

  public  PortfolioView() {
    setSpacing(10);
    setPadding(new Insets(30));

    Button exchangeBtn =  new Button(" Stock Exchange");
    Button transactionBtn =  new Button(" Stock Transaction");
    Button portfolioBtn =  new Button(" Stock Portfolio");

    HBox topMenu = new HBox(20);

    topMenu.getChildren().addAll(exchangeBtn, transactionBtn, portfolioBtn);

    moneyLabel = new Label();
    netWorthLabel = new Label();
    weekLabel = new Label();

    portfolioList = new ListView<>();
    portfolioList.setPrefHeight(300);
    portfolioList.setPrefWidth(500);

    sellQuantityField = new TextField();
    sellQuantityField.setPromptText("Enter quantity to sell");

    sellBtn = new Button("Sell selected");

    VBox centerBox = new VBox(20);
    centerBox.getChildren().addAll(
            moneyLabel,
            netWorthLabel,
            weekLabel,
            portfolioList,
            sellQuantityField,
            sellBtn
    );

    backBtn = new Button("Back");

    HBox bottomBox = new HBox();
    bottomBox.setAlignment(Pos.CENTER_LEFT);
    bottomBox.getChildren().addAll(backBtn);

    getChildren().addAll(topMenu, centerBox, bottomBox);
  }

  public ListView<Share> getPortfolioList() {
    return portfolioList;
  }

  public Label getMoneyLabel() {
    return moneyLabel;
  }

  public Label getNetWorthLabel() {
    return netWorthLabel;
  }

  public Label getWeekLabel() {
    return weekLabel;
  }

  public TextField getSellQuantityField() {
    return sellQuantityField;
  }

  public Button getSellBtn() {
    return sellBtn;
  }

  public Button getBackBtn() {
    return backBtn;
  }
}
