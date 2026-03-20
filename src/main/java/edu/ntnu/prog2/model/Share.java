package edu.ntnu.prog2.model;

import java.math.BigDecimal;

public class Share {
  private final Stock stock;
  private final BigDecimal quantity;
  private final BigDecimal purchasePrice;

    public Share(Stock stock, BigDecimal quantity, BigDecimal purchasePrice) {
        if (stock == null) {
            throw new IllegalArgumentException("stock cannot be null");
        }
        if (quantity == null) {
            throw new IllegalArgumentException("quantity cannot be null");
        }
        if (purchasePrice == null) {
            throw new IllegalArgumentException("purchasePrice cannot be null");
        }
        this.stock = stock;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

  public Stock getStock() {
    return stock;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public BigDecimal getPurchasePrice() {
    return purchasePrice;
  }
}
