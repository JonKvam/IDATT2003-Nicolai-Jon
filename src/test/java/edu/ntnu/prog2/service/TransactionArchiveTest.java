package edu.ntnu.prog2.service;

import edu.ntnu.prog2.calculator.PurchaseCalculator;
import edu.ntnu.prog2.calculator.SaleCalculator;
import edu.ntnu.prog2.calculator.TransactionCalculator;
import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.model.Stock;
import edu.ntnu.prog2.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionArchiveTest {

  private Stock stock;
  private Share share;
  private PurchaseCalculator purchaseCalculator;
  private SaleCalculator saleCalculator;
  private Purchase purchase1;
  private Purchase purchase2;
  private Purchase purchase3;
  private Sale sale1;
  private Sale sale2;
  private TransactionArchive transactionArchive;



  @BeforeEach
  void setup() {
    stock = new Stock("NVDA", "Nvidia", new BigDecimal("150"));
    share = new Share(stock, new BigDecimal("10"), new BigDecimal("100"));
    purchaseCalculator = new PurchaseCalculator(share);
    saleCalculator = new SaleCalculator(share);
    purchase1 = new Purchase(share, 1);
    purchase2 = new Purchase(share, 2);
    purchase3 = new Purchase(share, 1);
    sale1 = new Sale(share, 1);
    sale2 = new Sale(share, 4);
    transactionArchive = new TransactionArchive();
  }

  @Test
  void testAddPurchase() {
    assertTrue(transactionArchive.addTransaction(purchase1));
  }

  @Test
  void testAddSale() {
    assertTrue(transactionArchive.addTransaction(sale2));
  }

  @Test
  void testIsEmpty() {
    assertTrue(transactionArchive.isEmpty());
  }

  @Test
  void testIsNotEmpty() {
    transactionArchive.addTransaction(purchase2);
    assertFalse(transactionArchive.isEmpty());
  }

  @Test
  void getTransactions() {
    transactionArchive.addTransaction(purchase1);
    transactionArchive.addTransaction(purchase2);
    transactionArchive.addTransaction(purchase3);
    transactionArchive.addTransaction(sale1);
    transactionArchive.addTransaction(sale2);
    assertEquals(List.of(purchase1,purchase3, sale1),transactionArchive.getTransactions(1));
  }

  @Test
  void getPurchases() {
    transactionArchive.addTransaction(purchase1);
    transactionArchive.addTransaction(purchase2);
    transactionArchive.addTransaction(purchase3);
    assertEquals(List.of(purchase2), transactionArchive.getPurchases(2));
  }

  @Test
  void getSales() {
    transactionArchive.addTransaction(sale1);
    transactionArchive.addTransaction(sale2);
    assertEquals(List.of(sale2),transactionArchive.getSales(4));
  }

  @Test
  void countDistinctWeeks() {
    transactionArchive.addTransaction(purchase1);
    transactionArchive.addTransaction(purchase2);
    transactionArchive.addTransaction(purchase3);
    transactionArchive.addTransaction(sale1);
    transactionArchive.addTransaction(sale2);
    assertEquals(3,transactionArchive.countDistinctWeeks());
  }
}