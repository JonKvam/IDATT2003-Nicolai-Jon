package edu.ntnu.prog2.calculator;

import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.model.Stock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

class PurchaseCalculatorTest {
  private PurchaseCalculator calculator;
  private final BigDecimal PRICE =  new BigDecimal("10");
  private final BigDecimal QUANTITY =  new BigDecimal("5");

  @BeforeEach
  void setUp() {
    Stock stock = new Stock("NVDA", "Nvidia", PRICE);
    Share share = new Share(stock, QUANTITY, PRICE);
    calculator = new PurchaseCalculator(share);
  }

  @Test
  void testCalculateGross() {
    BigDecimal expectedGross = new BigDecimal("50");
    assertEquals(expectedGross, calculator.calculateGross());
  }

  @Test
  void testCalculateCommission() {
    BigDecimal expectedCommission = new BigDecimal("0.250");
    assertEquals(expectedCommission, calculator.calculateCommission());
  }

  @Test
  void testCalculateTax() {
    BigDecimal expectedTax = new BigDecimal("0");
    assertEquals(expectedTax, calculator.calculateTax());
  }

  @Test
  void testCalculateTotal() {
    BigDecimal expectedTotal = new BigDecimal("50.250");
    assertEquals(expectedTotal, calculator.calculateTotal());
  }
}