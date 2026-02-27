package edu.ntnu.prog2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class PortfolioTest {

  private Portfolio portfolio;
  private Share share1;
  private Share share2;

  @BeforeEach
  void setUp() {
    Stock stock = new Stock("NVDA", "Nvidia", valueOf(5));
    share1 = new Share(stock, valueOf(2), valueOf(10));
    share2 = new Share(stock, valueOf(1), valueOf(5));
    portfolio = new Portfolio();
  }

  @Test
  void testAddShare() {
    portfolio.addShare(share1);
    assertTrue(portfolio.contains(share1));
  }

  @Test
  void testRemoveShare() {
    portfolio.addShare(share1);
    portfolio.addShare(share2);
    portfolio.removeShare(share1);
    assertFalse(portfolio.contains(share1));
  }


}
