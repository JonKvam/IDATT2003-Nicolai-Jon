package edu.ntnu.prog2.service;

import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.model.Stock;
import edu.ntnu.prog2.model.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Exchange {
  private final String name;
  private int week;
  private final Map<String, Stock> stockMap;
  private final Random random;

  public Exchange(String name, List<Stock> stocks){
    this.name = name;
    this.week = 1;
    this.stockMap = new HashMap<>();
    this.random = new Random();

    for (Stock stock : stocks){
      stockMap.put(stock.getSymbol(), stock);
    }
  }

  public String getName() {
    return name;
  }

  public int getWeek() {
    return week;
  }

  public boolean hasStock(String symbol) {
    return stockMap.containsKey(symbol);
  }

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

  public List<Stock> findStocks(String searchTerm) {
    if (searchTerm == null || searchTerm.isEmpty()){
      throw new IllegalArgumentException("searchTerm cannot be null or empty");
    }
    List<Stock> stocks = new ArrayList<>();
    String lowerSearch = searchTerm.toLowerCase();
    for (Stock stock : stockMap.values()) {
      if (stock.getSymbol().toLowerCase().contains(lowerSearch) ||
              stock.getCompany().toLowerCase().contains(lowerSearch)) {

        stocks.add(stock);
      }
    }
    return stocks;
  }

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
