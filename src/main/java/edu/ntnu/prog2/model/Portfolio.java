package edu.ntnu.prog2.model;

import edu.ntnu.prog2.calculator.SaleCalculator;
import edu.ntnu.prog2.observer.Observer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for portfolios.
 *
 * <p>The class contains </p>
 *
 */
public class Portfolio {

  private final List<Share> shares;
  private final List<Observer> observers = new ArrayList<>();

  public Portfolio() {
    this.shares = new ArrayList<>();
  }

  /**
   * Add method for observer.
   *
   * @param observer the observer
   */
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  /**
   * Notify method for observers.
   */
  public void notifyObservers() {
    for (Observer observer : observers) {
      observer.update();
    }
  }

  public boolean addShare(Share share) {
    validateShare(share);
    if (shares.contains(share)) {
      throw new IllegalArgumentException("share already exists in portfolio");
    }
    notifyObservers();
    return shares.add(share);
  }

  public boolean removeShare(Share share) {
    validateShare(share);
    if (!shares.contains(share)) {
      throw new IllegalArgumentException("share does not exist in portfolio");
    }
    notifyObservers();
    return shares.remove(share);
  }

  public List<Share> getShares() {
    return List.copyOf(shares);
  }

  public boolean contains(Share share) {
    validateShare(share);
    return shares.contains(share);
  }

  public BigDecimal getNetWorth() {
    BigDecimal netWorth = BigDecimal.ZERO;
    SaleCalculator saleCalculator;
    for (Share share : shares) {
      saleCalculator = new SaleCalculator(share);
      netWorth = netWorth.add(saleCalculator.calculateTotal());
    }

    return netWorth;
  }

  private void validateShare(Share share) {
    if (share == null) {
      throw new IllegalArgumentException("share cannot be null");
    }
  }

}
