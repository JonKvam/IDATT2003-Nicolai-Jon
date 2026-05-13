package edu.ntnu.prog2.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representing a stock in a company.
 *
 * <p>The class represents a stock by using a company name, their symbol
 * and the price of a stock. The class consists of a constructor as well as
 * methods to access information as well as changing the value of stocks.</p>
 *
 * @author Nicolai
 */
public class Stock {
  private final String symbol;
  private final String company;
  private final List<BigDecimal> prices;

  /**
   * Constructor for stock.
   *
   * @param symbol distinct symbols to identify publicly traded companies
   * @param company name of represented company available for trade
   * @param salesPrice current value of stock
   */
  public Stock(final String symbol, final String company, final BigDecimal salesPrice) {
    this.symbol = symbol;
    this.company = company;
    this.prices = new ArrayList<>();
    this.prices.add(salesPrice);
  }

  /**
   * Get-method for symbol.
   *
   * @return symbol as string
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Get-method for company.
   *
   * @return company name as string
   */
  public String getCompany() {
    return company;
  }

  /**
   * Get-method for stock's sales price.
   *
   * @return value of stock as BigDecimal
   */
  public BigDecimal getSalesPrice() {
    if (prices.isEmpty()) {
      return BigDecimal.ZERO;
    }
    return prices.getLast();
  }

  /**
   * Adds a sales-price of a stock.
   *
   * @param price specific price to be added
   */
  public void addNewSalesPrice(BigDecimal price) {
    prices.add(price);
  }

  /**
   * Shows a copy of all prices tied to stock.
   *
   * @return list of all prices as BigDecimal values
   */
  public List<BigDecimal> getHistoricalPrices() {
    return new ArrayList<>(prices);
  }

  /**
   * Returns highest historical value of stock.
   *
   * @return highest price of stock as BigDecimal
   */
  public BigDecimal getHighestPrice() {
    if (prices.isEmpty()) {
      return BigDecimal.ZERO;
    }
    return Collections.max(prices);
  }

  /**
   * Returns lowest historical value of stock.
   *
   * @return lowest price of stock as BigDecimal
   */
  public BigDecimal getLowestPrice() {
    if (prices.isEmpty()) {
      return BigDecimal.ZERO;
    }
    return Collections.min(prices);
  }

  /**
   * Calculates price latest price change of stock.
   *
   * @return latest price change as BigDecimal value
   */
  public BigDecimal getLatestPriceChange() {
    if (prices.size() < 2) {
      return BigDecimal.ZERO;
    }
    BigDecimal last = prices.getLast();
    BigDecimal secondLast = prices.get(prices.size() - 2);
    return  last.subtract(secondLast);
  }

  @Override
  public String toString() {
    return symbol + " - " + company + " ($" + getSalesPrice() + ")";
  }
}