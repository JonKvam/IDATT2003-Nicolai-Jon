package edu.ntnu.prog2.model;

import edu.ntnu.prog2.calculator.SaleCalculator;
import edu.ntnu.prog2.observer.Observer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a portfolio of shares.
 *
 * <p>Supports adding, removing, and querying shares, as well as
 *  calculating the total net worth based on current share values.
 *  Implements the observer pattern to notify listeners of changes.</p>
 *
 * @author Nicolai and Jon
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
   * Notify all registered observers of change.
   */
  public void notifyObservers() {
    for (Observer observer : observers) {
      observer.update();
    }
  }

  /**
   * Method for adding shares to the portfolio.
   *
   * @param share a share to add to portfolio.
   *
   * @return true if share was added successfully
   * @throws IllegalArgumentException if the share already exist in the portfolio
   */
  public boolean addShare(Share share) {
    validateShare(share);
    if (shares.contains(share)) {
      throw new IllegalArgumentException("share already exists in portfolio");
    }
    notifyObservers();
    return shares.add(share);
  }

  /**
   * Removes a share from the portfolio.
   *
   * @param share a share to be removed from portfolio.
   * @return true if share is successfully removed
   * @throws IllegalArgumentException if share does not exist in portfolio
   */
  public boolean removeShare(Share share) {
    validateShare(share);
    if (!shares.contains(share)) {
      throw new IllegalArgumentException("share does not exist in portfolio");
    }
    notifyObservers();
    return shares.remove(share);
  }

  /**
   * Accesses an unmodifiable list of shares in portfolio.
   *
   * @return an unmodifiable list of shares
   */
  public List<Share> getShares() {
    return List.copyOf(shares);
  }

  /**
   * Checks to see if portfolio contains a specific share.
   *
   * @param share a share to be checked for in portfolio
   * @return return true if portfolio contains the share, false otherwise
   */
  public boolean contains(Share share) {
    validateShare(share);
    return shares.contains(share);
  }

  /**
   * Calculating total worth of portfolio.
   *
   * @return total sell-value of portfolio as BigDecimal.
   */
  public BigDecimal getNetWorth() {
    BigDecimal netWorth = BigDecimal.ZERO;
    SaleCalculator saleCalculator;
    for (Share share : shares) {
      saleCalculator = new SaleCalculator(share);
      netWorth = netWorth.add(saleCalculator.calculateTotal());
    }

    return netWorth;
  }

  /**
   * Method to be used in Portfolio-class to check that a share is not null.
   *
   * @param share a share to be validated
   * @throws IllegalArgumentException if share is null
   */
  private void validateShare(Share share) {
    if (share == null) {
      throw new IllegalArgumentException("share cannot be null");
    }
  }

}
