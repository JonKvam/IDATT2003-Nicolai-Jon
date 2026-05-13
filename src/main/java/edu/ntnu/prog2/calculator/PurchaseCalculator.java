package edu.ntnu.prog2.calculator;

import edu.ntnu.prog2.model.Share;
import java.math.BigDecimal;

/**
 *Subclass for purchase calculator.
 *
 *<p>This class implements transaction calculator and consists of a constructor and
 * different methods to calculate different costs regarding purchases </p>
 *
 * @author Nicolai
 */
public class PurchaseCalculator implements TransactionCalculator {

  private final BigDecimal purchasePrice;
  private final BigDecimal quantity;

  /**
   * Constructor for purchase calculator.
   *
   * @param share an instance of a share
   */
  public PurchaseCalculator(Share share) {
    this.purchasePrice = share.getPurchasePrice();
    this.quantity = share.getQuantity();
  }

  /**
   * Method for calculating gross value.
   *
   * @return gross value as BigDecimal
   */
  @Override
  public BigDecimal calculateGross() {
    return purchasePrice.multiply(quantity);
  }

  /**
   * Method for calculating tax on purchases.
   *
   * @return commission as BigDecimal.
   */
  @Override
  public BigDecimal calculateCommission() {
    BigDecimal commissionRate = new BigDecimal("0.005");
    return calculateGross().multiply(commissionRate);
  }

  /**
   * Method for calculating tax on purchases.
   *
   * @return tax as BigDecimal (always zero on purchases)
   */
  @Override
  public BigDecimal calculateTax() {
    return BigDecimal.ZERO;
  }

  /**
   * Method for calculating total cost of purchases.
   *
   * @return Total cost of purchase as BigDecimal
   */
  @Override
  public BigDecimal calculateTotal() {
    return calculateGross().add(calculateCommission()).add(calculateTax());
  }
}
