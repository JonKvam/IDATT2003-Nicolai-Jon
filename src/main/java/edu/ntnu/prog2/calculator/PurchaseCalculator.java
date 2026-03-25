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

  @Override
  public BigDecimal calculateGross() {
    return purchasePrice.multiply(quantity);
  }

  @Override
  public BigDecimal calculateCommission() {
    BigDecimal commissionRate = new BigDecimal("0.005");
    return calculateGross().multiply(commissionRate);
  }

  @Override
  public BigDecimal calculateTax() {
    return BigDecimal.ZERO;
  }

  @Override
  public BigDecimal calculateTotal() {
    return calculateGross().add(calculateCommission()).add(calculateTax());
  }
}
