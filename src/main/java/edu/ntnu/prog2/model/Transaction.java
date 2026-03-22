package edu.ntnu.prog2.model;

import edu.ntnu.prog2.calculator.TransactionCalculator;

public abstract class Transaction {
    private final Share share;
    private final int week;
    public final TransactionCalculator calculator;
    private boolean committed;

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

    protected void validateCommit(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player cannot be null");
        }
        if (committed) {
            throw new IllegalStateException("Transaction already committed");
        }
    }

    public abstract void commit(Player player);
}
