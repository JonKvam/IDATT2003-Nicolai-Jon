package edu.ntnu.prog2.model;

import java.math.BigDecimal;

/**
 * Representing a share of a company, a bundle of stocks.
 *
 * <p>The class represents a share. The share consists of a number of
 * stocks resulting in a value based on the amount of stocks. The class
 * consists of a constructor and some get-methods to access information. </p>
 *
 * @author Nicolai
 */
public class Share {
  private final Stock stock;
  private final BigDecimal quantity;
  private final BigDecimal purchasePrice;

  /**
   * Constructor for share.
   *
   * @param stock part of a company to be traded
   * @param quantity amount of stocks to be traded
   * @param purchasePrice value of stock
   */
  public Share(Stock stock, BigDecimal quantity, BigDecimal purchasePrice) {
      this.stock = stock;
      this.quantity = quantity;
      this.purchasePrice = purchasePrice;
  }

  /**
   * Get-method for stocks.
   *
   * @return stock as stock.
   */
  public Stock getStock() {
    return stock;
  }

  /**
   * Get-method for amount of stocks.
   *
   * @return amount of stocks as BigDecimal
   */
  public BigDecimal getQuantity() {
    return quantity;
  }

  /**
   * Get-method for value of stock.
   *
   * @return value of stock as BigDecimal
   */
  public BigDecimal getPurchasePrice() {
    return purchasePrice;
  }

  @Override
  public String toString() {

    return stock.getSymbol() + " - Quantity: " + quantity + " - Bought at: $" + purchasePrice;
  }
}
