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
    this.name = name;
    this.startingMoney = startingMoney;
    this.money = money;
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

  //What is the purpose of this method?
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
