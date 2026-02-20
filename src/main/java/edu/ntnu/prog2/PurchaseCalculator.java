package edu.ntnu.prog2;

import java.math.BigDecimal;

public class PurchaseCalculator implements TransactionCalculator{
    private final BigDecimal purchasePrice;
    private final BigDecimal quantity;

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
        return calculateGross().multiply(BigDecimal.valueOf(0.005));
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
