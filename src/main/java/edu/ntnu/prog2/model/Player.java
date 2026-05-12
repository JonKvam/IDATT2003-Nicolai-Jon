package edu.ntnu.prog2.model;

import edu.ntnu.prog2.observer.Observer;
import edu.ntnu.prog2.service.TransactionArchive;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for Player.
 *
 * <p>The class represents a player of the game. The class
 * has a constructor and methods to keep track of the players
 * actions, shares and balance. </p>
 */
public class Player {

  private final String name;
  private final BigDecimal startingMoney;
  private BigDecimal money;
  private final Portfolio portfolio;
  private final TransactionArchive transactionArchive;
  private final List<Observer> observers = new ArrayList<>();

  /**
   * Constructor for Player.
   *
   * @param name any String to represent name of player
   * @param startingMoney amount of money as BigDecimal representing money player begins with
   * @param money BigDecimal to represent ongoing balance of player
   */
  public Player(String name, BigDecimal startingMoney, BigDecimal money) {
    if (name == null) {
      throw new IllegalArgumentException("name cannot be null");
    }
    if (startingMoney == null) {
      throw new IllegalArgumentException("startingMoney cannot be null");
    }
    if (money == null) {
      throw new IllegalArgumentException("money cannot be null");
    }
    this.name = name;
    this.startingMoney = startingMoney;
    this.money = money;
    portfolio = new Portfolio();
    transactionArchive = new TransactionArchive();
  }

  /**
   *get() method for name.
   *
   * @return name of player as a String
   */
  public String getName() {
    return name;
  }

  /**
   *get() method for money.
   *
   * @return balance of player as BigDecimal.
   */
  public BigDecimal getMoney() {
    return money;
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

  /**
   * Method for adding money to a players account.
   *
   * @param amount amount to be added to players balance.
   */
  public void addMoney(BigDecimal amount) {
    if (money == null) return;
    money = money.add(amount);
    notifyObservers();
  }

  /**
   * Method for removing money from a players account.
   *
   * @param amount amount to be removed from a players balance.
   */
  public void withdrawMoney(BigDecimal amount) {
    if (money == null) return;
    money = money.subtract(amount);
    notifyObservers();
  }

  /**
   * Method for accessing players portfolio.
   *
   * @return Portfolio as List over players shares.
   */
  public Portfolio getPortfolio() {
    return portfolio;
  }

  /**
   * Method for accessing players transactions.
   *
   * @return transaction archive as List containing all transactions
   */
  public TransactionArchive getTransactionArchive() {
    return transactionArchive;
  }

  public BigDecimal getNetWorth() {
    return money.add(portfolio.getNetWorth());
  }

  /**
   * Method for getting a players trading status.
   *
   * @return trading status as a String.
   */
  public String getTradingStatus() {
    if (getTransactionArchive().countDistinctWeeks() >= 20
            && getNetWorth().compareTo(startingMoney.multiply(new BigDecimal("2"))) >= 0) {
      return "Speculator";
    } else if (getTransactionArchive().countDistinctWeeks() >= 10
              && getNetWorth().compareTo(startingMoney.multiply(new BigDecimal("1.2"))) >= 0) {
      return "Investor";
    } else {
      return "Novice";
    }
  }
}
