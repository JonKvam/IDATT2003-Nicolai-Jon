package edu.ntnu.prog2.service;

import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.model.Stock;
import edu.ntnu.prog2.model.Transaction;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Represents a stock exchange where players can buy and sell stocks.
 * The exchange keeps track of available stocks, current week, and simulates prices over time.
 */
public class Exchange {
  private final String name;
  private int week;
  private final Map<String, Stock> stockMap;
  private final Random random;

  /**
   * Constructs a new exchange with a given name and list of stocks.
   *
   * @param name the name of the exchange
   * @param stocks the initial list of stocks available
   */
  public Exchange(String name, List<Stock> stocks) {
    this.name = name;
    this.week = 1;
    this.stockMap = new HashMap<>();
    this.random = new Random();

    for (Stock stock : stocks) {
      stockMap.put(stock.getSymbol(), stock);
    }
  }

  /**
   * Returns the name of the exchange.
   *
   * @return the exchange name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the current week in the game.
   *
   * @return the current week
   */
  public int getWeek() {
    return week;
  }

  /**
   * Checks if the stock with the given symbol exists.
   *
   * @param symbol the symbol of the stock
   * @return true if the stock exists, false if it does not exist
   */
  public boolean hasStock(String symbol) {
    return stockMap.containsKey(symbol);
  }

  /**
   * Retrieves a stock by its symbol.
   *
   * @param symbol the stock symbol
   * @return the corresponding stock of the symbol
   * @throws IllegalArgumentException if symbol is null og empty
   * @throws NoSuchElementException if no stock with the symbol was found
   */
  public Stock getStock(String symbol) {
    if (symbol == null || symbol.isEmpty()) {
      throw new IllegalArgumentException("Symbol cannot be null or empty");
    }

    Stock stock = stockMap.get(symbol);

    if (stock == null) {
      throw new NoSuchElementException("Stock not found: " + symbol);
    }

    return stockMap.get(symbol);
  }

  /**
   * Searches for stocks matching the search term.
   *
   * @param searchTerm the term to search for
   * @return a list of stocks matching the search term
   * @throws IllegalArgumentException if search term is null or empty
   */
  public List<Stock> findStocks(String searchTerm) {
    if (searchTerm == null || searchTerm.isEmpty()) {
      throw new IllegalArgumentException("searchTerm cannot be null or empty");
    }
    List<Stock> stocks = new ArrayList<>();
    String lowerSearch = searchTerm.toLowerCase();
    for (Stock stock : stockMap.values()) {
      if (stock.getSymbol().toLowerCase().contains(lowerSearch)
              || stock.getCompany().toLowerCase().contains(lowerSearch)) {

        stocks.add(stock);
      }
    }
    return stocks;
  }

  /**
   * Creates a transaction for buying stocks.
   *
   * @param symbol the stock symbol
   * @param quantity the amount of shares to buy
   * @param player the player performing the purchase
   * @return a transaction representing the purchase
   * @throws IllegalArgumentException if player and quantity is null
   *                                  or player has insufficient funds
   */
  public Transaction buy(String symbol, BigDecimal quantity, Player player) {
    if (player == null) {
      throw new IllegalArgumentException("player cannot be null");
    }
    if (quantity == null ||  quantity.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("quantity cannot be negative");
    }

    Stock stock = getStock(symbol);
    BigDecimal totalPrice = stock.getSalesPrice().multiply(quantity);

    if (player.getMoney().compareTo(totalPrice) < 0) {
      throw new IllegalArgumentException("Insufficient money");
    }

    Share share = new Share(stock, quantity, stock.getSalesPrice());
    return new Purchase(share, week);
  }

  /**
   * Creates a transaction for selling stocks.
   *
   * @param share the share to sell
   * @param player the player performing the sale
   * @return a transaction representing the sale
   * @throws IllegalArgumentException if player is null, share is null or quantity is negative
   */
  public Transaction sell(Share share, Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }
    if (share == null) {
      throw new IllegalArgumentException("Share cannot be null");
    }
    if (share.getQuantity().compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Quantity must be greater than 0");
    }

    return new Sale(share, week);
  }

  /**
   * Advances the game by one week and updates the stock prices.
   * The prices change randomly with a small percentage range.
   */
  public void advance() {
    week++;
    for (Stock stock : stockMap.values()) {
      BigDecimal currentPrice = stock.getSalesPrice();
      double changePercent = (random.nextDouble() - 0.5) * 0.10;
      BigDecimal changeFactor = BigDecimal.valueOf(1 + changePercent);
      BigDecimal newPrice = currentPrice.multiply(changeFactor).setScale(2, RoundingMode.HALF_UP);
      if (newPrice.compareTo(BigDecimal.ONE) > 0) {
        newPrice = newPrice.add(BigDecimal.ONE);
      }
      stock.addNewSalesPrice(newPrice);
    }
  }

  /**
   * Returns the top gaining stocks based on the latest price change.
   *
   * @param limit the maximum number of stocks to be shown
   * @return a list of the top gaining stocks
   * @throws IllegalArgumentException if limit is negative
   */
  public List<Stock> getGainers(int limit) {
    if  (limit < 0) {
      throw new IllegalArgumentException("limit cannot be negative");
    }
    return stockMap.values().stream()
            .filter(stock -> stock.getLatestPriceChange().compareTo(BigDecimal.ZERO) > 0)
            .sorted((s1, s2) -> s2.getLatestPriceChange().compareTo(s1.getLatestPriceChange()))
            .limit(limit)
            .toList();
  }

  /**
   * Returns the top losing stocks based on the latest price change.
   *
   * @param limit the maximum number of stocks to be shown
   * @return a list of the top losing stocks
   * @throws IllegalArgumentException if limit is negative
   */
  public List<Stock> getLosers(int limit) {
    if (limit < 0) {
      throw new IllegalArgumentException("limit cannot be negative");
    }
    return stockMap.values().stream()
            .filter(stock -> stock.getLatestPriceChange().compareTo(BigDecimal.ZERO) < 0)
            .sorted(Comparator.comparing(Stock::getLatestPriceChange))
            .limit(limit)
            .toList();
  }
}
