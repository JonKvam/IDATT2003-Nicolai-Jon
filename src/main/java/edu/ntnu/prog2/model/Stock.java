package edu.ntnu.prog2.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stock {
  private final String symbol;
  private final String company;
  private final List<BigDecimal> prices;

    public Stock(final String symbol, final String company, final BigDecimal salesPrice) {
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

  public List<BigDecimal> getHistoricalPrices() {
    return new ArrayList<>(prices);
  }

  public BigDecimal getHighestPrice() {
      if (prices.isEmpty()) {
      return BigDecimal.ZERO;
      }
      return Collections.max(prices);
  }

  public BigDecimal getLowestPrice() {
      if (prices.isEmpty()) {
        return BigDecimal.ZERO;
      }
      return Collections.min(prices);
  }

  public BigDecimal getLatestPriceChange() {
      if (prices.size() < 2) {
        return BigDecimal.ZERO;
      }
      BigDecimal last = prices.getLast();
      BigDecimal secondLast = prices.get(prices.size() - 2);
      return  last.subtract(secondLast);
  }
}