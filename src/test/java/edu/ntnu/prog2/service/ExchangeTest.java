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

  @Test
  public void testGetStockThrowException() {
    assertThrows(IllegalArgumentException.class, () -> exchange.getStock(null));
    assertThrows(IllegalArgumentException.class, () -> exchange.getStock(""));
  }

  @Test
  public void testFindStocksThrowException() {
    assertThrows(IllegalArgumentException.class, () -> exchange.findStocks(null));
    assertThrows(IllegalArgumentException.class, () -> exchange.findStocks(""));
  }

  @Test
  public void testBuyThrowException() {
    Player poorPlayer = new Player("poor", BigDecimal.valueOf(1), BigDecimal.ZERO);
    assertThrows(IllegalArgumentException.class, () -> exchange.buy("AAPL", BigDecimal.valueOf(10), poorPlayer));
    assertThrows(IllegalArgumentException.class, () -> exchange.buy("AAPL",BigDecimal.valueOf(2), null));
    assertThrows(IllegalArgumentException.class, () -> exchange.buy("AAPL",BigDecimal.valueOf(-1), player));
  }

  @Test
  public void testSellThrowException() {
    Share share = new Share(stock, BigDecimal.valueOf(1), PRICE);
    Share illegalQuantityShare = new Share(stock, BigDecimal.valueOf(-1), PRICE);
    assertThrows(IllegalArgumentException.class, () -> exchange.sell(share, null));
    assertThrows(IllegalArgumentException.class, () -> exchange.sell(null, player));
    assertThrows(IllegalArgumentException.class, () -> exchange.sell(illegalQuantityShare, player));
  }

  @Test
  public void testGetGainers() {
    Stock s1 = new Stock("A", "A", BigDecimal.valueOf(100));
    Stock s2 = new Stock("B", "B", BigDecimal.valueOf(100));
    Stock s3 = new Stock("C", "C", BigDecimal.valueOf(100));

    s1.addNewSalesPrice(BigDecimal.valueOf(110));
    s2.addNewSalesPrice(BigDecimal.valueOf(105));
    s3.addNewSalesPrice(BigDecimal.valueOf(90));

    Exchange ex = new Exchange("Test", List.of(s1, s2, s3));

    List<Stock> gainers = ex.getGainers(2);

    assertEquals(2, gainers.size());
    assertEquals("A", gainers.getFirst().getSymbol());
    assertEquals("A", gainers.getFirst().getCompany());
  }

  @Test
  public void testGetLosers() {
    Stock s1 = new Stock("A", "A", BigDecimal.valueOf(100));
    Stock s2 = new Stock("B", "B", BigDecimal.valueOf(100));
    Stock s3 = new Stock("C", "C", BigDecimal.valueOf(100));

    s1.addNewSalesPrice(BigDecimal.valueOf(90));
    s2.addNewSalesPrice(BigDecimal.valueOf(95));
    s3.addNewSalesPrice(BigDecimal.valueOf(110));

    Exchange ex = new Exchange("Test", List.of(s1, s2, s3));

    List<Stock> losers = ex.getLosers(2);

    assertEquals(2, losers.size());
    assertEquals("A", losers.getFirst().getSymbol());
    assertEquals("A", losers.getFirst().getCompany());
  }
}