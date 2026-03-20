package edu.ntnu.prog2.service;

import edu.ntnu.prog2.Purchase;
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
    return stockMap.get(symbol);
  }

  public List<Stock> findStocks(String searchTerm) {
    List<Stock> stocks = new ArrayList<>();
    String lowerSearch = searchTerm.toLowerCase();
    for (Stock stock : stockMap.values()) {
      if (stock.getSymbol().toLowerCase().contains(lowerSearch) || stock.getCompany().contains(lowerSearch)) {
        stocks.add(stock);
      }
    }
    return stocks;
  }

  public Transaction buy(String symbol, BigDecimal quantity, Player player) {
    Stock stock = getStock(symbol);
    Share share = new Share(stock, quantity, stock.getSalesPrice());
    return new Purchase(share, week);
  }

  public Transaction sell(Share share, Player player) {
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
}
