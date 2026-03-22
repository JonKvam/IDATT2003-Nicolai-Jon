package edu.ntnu.prog2.service;

import edu.ntnu.prog2.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

class ExchangeTest {
  private Stock stock;
  private Player player;
  private Exchange exchange;

  private final BigDecimal PRICE = new BigDecimal("150");

  @BeforeEach
  void  setUp() {
    stock = new Stock("AAPL", "Apple", PRICE);
    this.exchange = new Exchange("Test exchange", List.of(stock));
    this.player = new Player("TestPlayer", BigDecimal.valueOf(10000), BigDecimal.valueOf(100));
  }

  @Test
  public void testGetName() {
    assertEquals("Test exchange", exchange.getName());
  }

  @Test
  public void testGetWeek() {
    assertEquals(1, exchange.getWeek());
  }

  @Test
  public void testGetStock() {
    Stock result = exchange.getStock("AAPL");
    assertNotNull(result);
    assertEquals("AAPL", result.getSymbol());
    assertEquals("Apple", result.getCompany());
  }

  @Test
  public void testHasStockWhenStockExists() {
    assertTrue(exchange.hasStock("AAPL"));
  }

  @Test
  public void testHasStockWhenStockDoesNotExist() {
    assertFalse(exchange.hasStock("NVDA"));
  }

  @Test
  public void testFindPrice() {
    List<Stock> companyResult  = exchange.findStocks("app");
    List<Stock> symbolResult  = exchange.findStocks("AAPL");
    assertEquals(1, companyResult.size());
    assertEquals(1, symbolResult.size());
  }

  @Test
  public void testBuy() {
    Transaction transaction = exchange.buy("AAPL", BigDecimal.valueOf(2), player);
    assertNotNull(transaction);
  }

  @Test
  public void testSell() {
    Share share = new Share(stock, BigDecimal.valueOf(2), BigDecimal.valueOf(100));
    Transaction transaction = exchange.sell(share, player);
    assertNotNull(transaction);
  }

  @Test
  public void testAdvance() {
    BigDecimal before = stock.getSalesPrice();
    exchange.advance();
    BigDecimal after = stock.getSalesPrice();
    assertNotEquals(before, after);
  }
}