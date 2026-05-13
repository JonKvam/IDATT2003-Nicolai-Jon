package edu.ntnu.prog2.view;

import edu.ntnu.prog2.model.Share;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PortfolioView extends VBox {
  private ListView<Share> portfolioList;
  private Label moneyLabel;
  private Label weekLabel;
  private Button sellBtn;
  private Button backBtn;

  public  PortfolioView() {
    setSpacing(10);
    setPadding(new Insets(30));

    Button exchangeBtn =  new Button(" Stock Exchange");
    Button transactionBtn =  new Button(" Stock Transaction");
    Button portfolioBtn =  new Button(" Stock Portfolio");

    HBox topMenu = new HBox(20);

    topMenu.getChildren().addAll(exchangeBtn, transactionBtn, portfolioBtn);

    moneyLabel = new Label();
    weekLabel = new Label();

    portfolioList = new ListView<>();
    portfolioList.setPrefHeight(300);
    portfolioList.setPrefWidth(500);

    sellBtn = new Button("Sell selected");

    VBox centerBox = new VBox(20);
    centerBox.getChildren().addAll(moneyLabel, weekLabel, portfolioList, sellBtn);

    backBtn = new Button("Back");
    HBox bottomBox = new HBox();
    bottomBox.setAlignment(Pos.CENTER_LEFT);
    bottomBox.getChildren().addAll(backBtn);

    getChildren().addAll(topMenu, centerBox, bottomBox);
  }

  public ListView<Share> getPortfolioList() {
    return portfolioList;
  }

  public Button getSellBtn() {
    return sellBtn;
  }

  public Button getBackBtn() {
    return backBtn;
  }
}
