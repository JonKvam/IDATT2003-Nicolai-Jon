package edu.ntnu.prog2.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Stock {
    private final String symbol;
    private final String company;
    private final List<BigDecimal> prices;

    public Stock(final String symbol, final String company, final BigDecimal salesPrice) {
        if (symbol == null) {
            throw new IllegalArgumentException("symbol cannot be null");
        }
        if (company == null) {
            throw new IllegalArgumentException("company cannot be null");
        }
        if (salesPrice == null) {
            throw new IllegalArgumentException("salesPrice cannot be null");
        }
        this.symbol = symbol;
        this.company = company;
        this.prices = new ArrayList<>();
        this.prices.add(salesPrice);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompany() {
        return company;
    }

    public BigDecimal getSalesPrice() {
        if (prices.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return prices.getLast();
    }

    public void addNewSalesPrice(BigDecimal price) {
            prices.add(price);
    }
}