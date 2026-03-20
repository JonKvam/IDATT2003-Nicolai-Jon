package edu.ntnu.prog2.calculator;

import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.model.Stock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
class SaleCalculatorTest {
  private SaleCalculator calculator;
  private final BigDecimal PURCHASE_PRICE = BigDecimal.valueOf(100);
  private final BigDecimal SALES_PRICE = BigDecimal.valueOf(150);
  private final BigDecimal QUANTITY = BigDecimal.valueOf(10);

  @BeforeEach
  void setUp() {
    Stock stock = new Stock("NVDA", "Nvidia", SALES_PRICE);
    Share share = new Share(stock, QUANTITY, PURCHASE_PRICE);
    calculator = new SaleCalculator(share);
  }

  @Test
  public void testCalculateGross() {
    BigDecimal expectedGross = new BigDecimal("1500");
    assertEquals(expectedGross, calculator.calculateGross());
  }

  @Test
  public void testCalculateCommission() {
    BigDecimal expectedCommission = new BigDecimal("15.00");
    assertEquals(expectedCommission, calculator.calculateCommission());
  }

  @Test
  public void testCalculateTax() {
    BigDecimal expectedTax = new BigDecimal("145.500");
    assertEquals(expectedTax, calculator.calculateTax());
  }

  @Test
  public void testCalculateTotal() {
    BigDecimal expectedTotal = new BigDecimal("1339.500");
    assertEquals(expectedTotal, calculator.calculateTotal());
  }
}