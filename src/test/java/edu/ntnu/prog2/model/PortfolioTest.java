package edu.ntnu.prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioTest {


  private Stock stock1;
  private Stock stock2;
  private Share share1;
  private Share share2;
  private Portfolio testPortfolio;

  @BeforeEach
  void setup() {
    stock1 = new Stock("NVDA", "Nvidia", new BigDecimal("500"));
    stock2 = new Stock("AAP", "Apple", new BigDecimal("200"));
    share1 = new Share(stock1, new BigDecimal("3"), new BigDecimal("1500"));
    share2 = new Share(stock2, new BigDecimal("10"), new BigDecimal("2000"));
    testPortfolio = new Portfolio();
  }

  @Test
  void testAddShare() {
    testPortfolio.addShare(share1);
    testPortfolio.addShare(share2);
    assertEquals(List.of(share1, share2), testPortfolio.getShares());
  }

  @Test
  void testRemoveShare() {
    testPortfolio.addShare(share1);
    testPortfolio.addShare(share2);
    testPortfolio.removeShare(share1);
    assertEquals(List.of(share2), testPortfolio.getShares());
  }

  @Test
  void testGetShares() {
    testPortfolio.addShare(share1);
    assertEquals(List.of(share1), testPortfolio.getShares());
  }

  @Test
  void testContains() {
    testPortfolio.addShare(share1);
    testPortfolio.addShare(share2);
    assertTrue(testPortfolio.contains(share1));
  }

  @Test
  void testNotContains() {
    testPortfolio.addShare(share1);
    testPortfolio.addShare(share2);
    testPortfolio.removeShare(share1);
    assertFalse(testPortfolio.contains(share1));
  }

}