package edu.ntnu.prog2.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

class StockTest {
  private Stock stock;
  private final String SYMBOL = "NVDA";
  private final String COMPANY = "Nvidia";
  private final BigDecimal PRICE = new BigDecimal("150");

  @BeforeEach
  void setUp() {
    stock = new Stock(SYMBOL, COMPANY, PRICE);
  }

  @Test
  void testGetSymbol() {
    assertEquals(SYMBOL, stock.getSymbol());
  }

  @Test
  void testGetCompany() {
    assertEquals(COMPANY, stock.getCompany());
  }

  @Test
  void testGetSalesPrice() {
    assertEquals(PRICE, stock.getSalesPrice());
  }

  @Test
  void testAddNewSalesPrice() {
    stock.addNewSalesPrice(new BigDecimal("170"));
    assertEquals(new BigDecimal("170"), stock.getSalesPrice());
  }
}