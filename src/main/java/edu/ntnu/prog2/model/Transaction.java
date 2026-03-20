package edu.ntnu.prog2.model;

import edu.ntnu.prog2.calculator.TransactionCalculator;

public abstract class Transaction {
  private final Share share;
  private final int week;
  private final TransactionCalculator calculator;
  private boolean committed;

  public Transaction(Share share, int week, TransactionCalculator calculator) {
    if (share == null) {
      throw new IllegalArgumentException("share cannot be null");
    }
    if (week < 0) {
      throw new IllegalArgumentException("week cannot be negative");
    }
    if (calculator == null) {
      throw new IllegalArgumentException("calculator cannot be null");
    }
    this.share = share;
    this.week = week;
    this.calculator = calculator;
    this.committed = false;
  }

  public Share getShare() {
    return share;
  }

  public int getWeek() {
    return week;
  }

  public TransactionCalculator getCalculator() {
    return calculator;
  }

  public boolean isCommitted() {
    return committed;
  }

  protected void setCommitted(boolean committed) {
    this.committed = committed;
  }

  public abstract void commit(Player player);
}
