package edu.ntnu.prog2;

import java.math.BigDecimal;

public class SaleCalculator implements TransactionCalculator{
    private final BigDecimal purchasePrice;
    private final BigDecimal salesPrice;
    private final BigDecimal quantity;

    public SaleCalculator(Share share) {
        this.purchasePrice = share.getPurchasePrice();
        this.salesPrice = share.getStock().getSalesPrice();
        this.quantity = share.getQuantity();
    }

    @Override
    public BigDecimal calculateGross() {
        return salesPrice.multiply(quantity);
    }

    @Override
    public BigDecimal calculateCommission() {
        return calculateGross().multiply(BigDecimal.valueOf(0.01));
    }

    @Override
    public BigDecimal calculateTax() {
        BigDecimal gross = calculateGross();
        BigDecimal commission = calculateCommission();
        BigDecimal purchaseCost = purchasePrice.multiply(quantity);
        BigDecimal profit = gross.subtract(commission).subtract(purchaseCost);

        return profit.multiply(BigDecimal.valueOf(0.3));
    }

    @Override
    public BigDecimal calculateTotal() {
        return calculateGross().subtract(calculateCommission()).subtract(calculateTax());
    }
}
