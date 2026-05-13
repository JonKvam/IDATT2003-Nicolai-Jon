package edu.ntnu.prog2.model;

import edu.ntnu.prog2.calculator.TransactionCalculator;

/**
 * Superclass representing a transaction of a share.
 *
 * <p>Constructor and methods to be used as assets for subclasses
 * making it possible to do transactions with shares and display relevant information</p>
 *
 * @author Nicolai
 */
public abstract class Transaction {
  private final Share share;
  private final int week;
  public final TransactionCalculator calculator;
  private boolean committed;

  /**
   * Constructor for transaction with share, week and calculator.
   *
   * @param share specific share to be traded
   * @param week the week the transaction is processed
   * @param calculator type of calculator used, based on type of transaction
   * @throws IllegalArgumentException if share or calculator is null or if week is less than 1
   */
  public Transaction(Share share, int week, TransactionCalculator calculator) {
    if (share == null) {
      throw new IllegalArgumentException("share cannot be null");
    }
    if (calculator == null) {
      throw new IllegalArgumentException("calculator cannot be null");
    }
    if (week < 1) {
      throw new IllegalArgumentException("Week cannot be less than 1");
    }
    this.share = share;
    this.week = week;
    this.calculator = calculator;
    this.committed = false;
  }

  /**
   * Get-method for share.
   *
   * @return share as a Share.
   */
  public Share getShare() {
    return share;
  }

  /**
   * Get-method for transaction-week.
   *
   * @return week as an integer
   */
  public int getWeek() {
    return week;
  }

  /**
   * Get-method for type of calculator.
   *
   * @return type of calculator as TransactionCalculator
   */
  public TransactionCalculator getCalculator() {
    return calculator;
  }

  /**
   * Checks that a transaction is committed.
   *
   * @return true if transaction is committed, otherwise false
   */
  public boolean isCommitted() {
    return committed;
  }

  /**
   * Set-method to whether transaction is committed.
   *
   * @param committed takes in boolean value to change committed to
   */
  protected void setCommitted(boolean committed) {
    this.committed = committed;
  }

  /**
   * Verifies that a transaction can be committed.
   *
   * @param player player that is committing transaction
   * @throws IllegalArgumentException if player is null
   * @throws IllegalStateException if transaction is already committed
   */
  protected void validateCommit(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("player cannot be null");
    }
    if (committed) {
      throw new IllegalStateException("Transaction already committed");
    }
  }

  /**
   * Method to be used for subclasses to commit purchase.
   *
   * @param player player to commit purchase
   */
  public abstract void commit(Player player);
}
