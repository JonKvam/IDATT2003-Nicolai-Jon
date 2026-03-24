package edu.ntnu.prog2.model;

import edu.ntnu.prog2.service.Purchase;
import edu.ntnu.prog2.service.Sale;
import edu.ntnu.prog2.service.TransactionArchive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  private String name;
  private BigDecimal startingMoney;
  private BigDecimal money;
  private Portfolio portfolio;
  private TransactionArchive transactionArchive;
  private Share share1;
  private Share share2;
  private Stock stock1;
  private Stock stock2;
  private Purchase purchase1;
  private Purchase purchase2;
  private Sale sale1;
  private Player testPlayer;


  @BeforeEach
  void setup() {
    name = "testMan";
    startingMoney = new BigDecimal("5000");
    money = new BigDecimal("2000");
    stock1 = new Stock("NVDA", "Nvidia", new BigDecimal("500"));
    stock2 = new Stock("AAP", "Apple", new BigDecimal("200"));
    share1 = new Share(stock1, new BigDecimal("3"), new BigDecimal("1500"));
    share2 = new Share(stock2, new BigDecimal("10"), new BigDecimal("2000"));
    purchase1 = new Purchase(share1, 1);
    purchase2 = new Purchase(share2, 1);
    sale1 = new Sale(share1, 2);
    portfolio = new Portfolio();
    transactionArchive = new TransactionArchive();
    testPlayer = new Player(name, startingMoney, money);

  }

  @Test
  void getName() {

  }

  @Test
  void getMoney() {
  }

  @Test
  void addMoney() {
  }

  @Test
  void withdrawMoney() {
  }

  @Test
  void getPortfolio() {
    portfolio.addShare(share1);
  }

  @Test
  void getTransactionArchive() {
    transactionArchive.addTransaction(purchase1);
    transactionArchive.addTransaction(purchase2);
    transactionArchive.addTransaction(sale1);
  }
}