package edu.ntnu.prog2.model;

import edu.ntnu.prog2.service.Purchase;
import edu.ntnu.prog2.service.Sale;
import edu.ntnu.prog2.service.TransactionArchive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerSatusTest {

  private String name;
  private BigDecimal startingMoney;
  private BigDecimal money;
  private Portfolio portfolio;
  private TransactionArchive transactionArchive;
  private Player testPlayer;
  private Stock stock1, stock2, stock3, stock4, stock5;
  private Share share1, share2, share3, share4, share5, share6, share7, share8, share9, share10;
  private Purchase p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;
  private Sale s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;

  @BeforeEach
  void setup() {
    name = "testPlayer";
    startingMoney = new BigDecimal("5000");
    money = new BigDecimal("2000");
    testPlayer = new Player(name, startingMoney, money);
    stock1 = new Stock("TST1", "Test1", new BigDecimal("100"));
    stock2 = new Stock("TST2", "Test2", new BigDecimal("100"));
    stock3 = new Stock("TST3", "Test3", new BigDecimal("100"));
    stock4 = new Stock("TST4", "Test4", new BigDecimal("100"));
    stock5 = new Stock("TST5", "Test5", new BigDecimal("100"));
    share1 = new Share(stock1, new BigDecimal("1"), new BigDecimal("100"));
    share2 = new Share(stock2, new BigDecimal("2"), new BigDecimal("200"));
    share3 = new Share(stock3, new BigDecimal("3"), new BigDecimal("300"));
    share4 = new Share(stock4, new BigDecimal("4"), new BigDecimal("400"));
    share5 = new Share(stock5, new BigDecimal("5"), new BigDecimal("500"));
    share6 = new Share(stock1, new BigDecimal("6"), new BigDecimal("600"));
    share7 = new Share(stock2, new BigDecimal("7"), new BigDecimal("700"));
    share8 = new Share(stock3, new BigDecimal("8"), new BigDecimal("800"));
    share9 = new Share(stock4, new BigDecimal("9"), new BigDecimal("900"));
    share10 = new Share(stock5, new BigDecimal("10"), new BigDecimal("1000"));
    p1 = new Purchase(share1, 1);
    p2 = new Purchase(share2, 2);
    p3 = new Purchase(share3, 3);
    p4 = new Purchase(share4, 4);
    p5 = new Purchase(share5, 5);
    p6 = new Purchase(share6, 6);
    p7 = new Purchase(share7, 7);
    p8 = new Purchase(share8, 8);
    p9 = new Purchase(share9, 9);
    p10 = new Purchase(share10, 10);
    s1 = new Sale(share1, 11);
    s2 = new Sale(share2, 12);
    s3 = new Sale(share3, 13);
    s4 = new Sale(share4, 14);
    s5 = new Sale(share5, 15);
    s6 = new Sale(share6, 16);
    s7 = new Sale(share7, 17);
    s8 = new Sale(share8, 18);
    s9 = new Sale(share9, 19);
    s10 = new Sale(share10, 20);
  }

  @Test
  void testGetPlayerStatus_noviceHighNetWorthNoTransactions() {
    testPlayer.addMoney(new BigDecimal("10000"));
    assertEquals("Novice", testPlayer.getTradingStatus());
  }

  @Test
  void testGetPlayerStatus_lowNetWorthManyWeeks() {
    Purchase[] purchases = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10};
    Sale[] sales = {s1, s2, s3, s4, s5, s6, s7, s8, s9, s10};
    for (int i = 0; i < purchases.length; i++) {
      testPlayer.getTransactionArchive().addTransaction(purchases[i]);
      testPlayer.getTransactionArchive().addTransaction(sales[i]);
    }
    assertEquals("Novice", testPlayer.getTradingStatus());
  }

  @Test
  void testGetPlayerStatus_Investor() {
    testPlayer.addMoney(new BigDecimal("10000"));
    Purchase[] purchases = {p1, p2, p3, p4, p5, p6, p7, p8, p9};
    Sale[] sales = {s1, s2, s3, s4, s5, s6, s7, s8, s9};
    for (int i = 0; i < purchases.length; i++) {
      testPlayer.getTransactionArchive().addTransaction(purchases[i]);
      testPlayer.getTransactionArchive().addTransaction(sales[i]);
    }
    assertEquals("Investor", testPlayer.getTradingStatus());
  }

  @Test
  void testGetPlayerStatus_Speculator() {
    testPlayer.addMoney(new BigDecimal("10000"));
    Purchase[] purchases = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10};
    Sale[] sales = {s1, s2, s3, s4, s5, s6, s7, s8, s9, s10};
    for (int i = 0; i < purchases.length; i++) {
      testPlayer.getTransactionArchive().addTransaction(purchases[i]);
      testPlayer.getTransactionArchive().addTransaction(sales[i]);
    }
    assertEquals("Speculator", testPlayer.getTradingStatus());
  }
}
