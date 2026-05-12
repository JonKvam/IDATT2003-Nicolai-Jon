package edu.ntnu.prog2.calculator;

import edu.ntnu.prog2.model.Share;

import java.math.BigDecimal;

/**
 *Subclass for sale calculator.
 *
 *<p>This class implements transaction calculator and consists of a constructor and
 * different methods to calculate different costs regarding sales </p>
 *
 * @author Nicolai
 */
public class SaleCalculator implements TransactionCalculator {
  private final BigDecimal purchasePrice;
  private final BigDecimal salesPrice;
  private final BigDecimal quantity;

  /**
   * Constructor for sale calculator.
   *
   * @param share an instance of a share
   */
  public SaleCalculator(Share share) {
    this.purchasePrice = share.getPurchasePrice();
    this.salesPrice = share.getStock().getSalesPrice();
    this.quantity = share.getQuantity();
  }

  /**
   * Method for calculating gross value on sales.
   *
   * @return gross value as BigDecimal
   */
  @Override
  public BigDecimal calculateGross() {
    return salesPrice.multiply(quantity);
  }

  /**
   * Method for calculating tax on sales.
   *
   * @return commission as BigDecimal.
   */
  @Override
  public BigDecimal calculateCommission() {
    BigDecimal commissionRate = new BigDecimal("0.01");
    return calculateGross().multiply(commissionRate);
  }

  /**
   * Method for calculating tax on sales.
   *
   * @return tax as BigDecimal
   */
  @Override
  public BigDecimal calculateTax() {
    BigDecimal gross = calculateGross();
    BigDecimal commission = calculateCommission();
    BigDecimal purchaseCost = purchasePrice.multiply(quantity);
    BigDecimal profit = gross.subtract(commission).subtract(purchaseCost);
    BigDecimal taxRate = new BigDecimal("0.3");

    return profit.multiply(taxRate);
  }

  /**
   * Method for calculating total value of sales.
   *
   * @return Total sale value as BigDecimal
   */
  @Override
  public BigDecimal calculateTotal() {
    return calculateGross().subtract(calculateCommission()).subtract(calculateTax());
  }
}
