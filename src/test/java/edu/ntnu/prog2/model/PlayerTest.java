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
    purchase2 = new Purchase(share2, 2);
    sale1 = new Sale(share1, 2);
    testPlayer = new Player(name, startingMoney, money);

  }

  @Test
  void testGetName() {
    assertEquals("testMan", testPlayer.getName());
  }

  @Test
  void testGetMoney() {
    assertEquals(new BigDecimal("2000"), testPlayer.getMoney());
  }

  @Test
  void testAddMoney() {
    testPlayer.addMoney(new BigDecimal("500"));
    assertEquals(new BigDecimal("2500"), testPlayer.getMoney());
  }

  @Test
  void testWithdrawMoney() {
    testPlayer.withdrawMoney(new BigDecimal("500"));
    assertEquals(new BigDecimal("1500"), testPlayer.getMoney());
  }

  @Test
  void getPortfolio() {
    testPlayer.getPortfolio().addShare(share1);
    testPlayer.getPortfolio().addShare(share2);
    assertEquals(List.of(share1, share2), testPlayer.getPortfolio().getShares());
  }

  @Test
  void getTransactionArchive() {
    testPlayer.getTransactionArchive().addTransaction(purchase1);
    testPlayer.getTransactionArchive().addTransaction(purchase2);
    testPlayer.getTransactionArchive().addTransaction(sale1);
    assertEquals(List.of(purchase2, sale1), testPlayer.getTransactionArchive().getTransactions(2));
  }
}