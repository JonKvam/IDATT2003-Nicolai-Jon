package edu.ntnu.prog2.model;

import edu.ntnu.prog2.service.TransactionArchive;

import java.math.BigDecimal;

public class Player {

  private final String name;
  private BigDecimal startingMoney;
  private BigDecimal money;
  private Portfolio portfolio;
  private TransactionArchive transactionArchive;

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
    //TODO
  }

  public String getName() {
    return name;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public void addMoney(BigDecimal amount) {
    if(money == null) return;
    money = money.add(amount);
  }

  public void withdrawMoney(BigDecimal amount) {
    if(money == null) return;
    money = money.subtract(amount);
  }

  public Portfolio getPortfolio() {
    return portfolio;
  }

  public TransactionArchive getTransactionArchive() {
    return transactionArchive;
  }
}
