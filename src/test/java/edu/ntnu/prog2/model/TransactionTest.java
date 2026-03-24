package edu.ntnu.prog2.model;

import edu.ntnu.prog2.calculator.TransactionCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
  private Share share;
  private TransactionCalculator calculator;
  private TestTransaction transaction;

  private static class TestTransaction extends Transaction {
    public TestTransaction(Share share, int week, TransactionCalculator calculator) {
      super(share, week, calculator);
    }

    @Override
    public void commit(Player player) {
      validateCommit(player);
      setCommitted(true);
    }
  }

  private static class StubTransactionCalculator implements TransactionCalculator {
    @Override
    public BigDecimal calculateGross() {
      return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calculateCommission() {
      return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calculateTax() {
      return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calculateTotal() {
      return BigDecimal.ZERO;
    }
  }

  @BeforeEach
  void setUp() {
    Stock stock = new Stock("NVDA", "Nvidia", new BigDecimal("150"));
    share = new Share(stock, new BigDecimal("10"), new BigDecimal("100"));
    calculator = new StubTransactionCalculator();
    transaction = new TestTransaction(share, 3, calculator);
  }

  @Test
  void constructorStoresValuesAndStartsUncommitted() {
    assertEquals(share, transaction.getShare());
    assertEquals(3, transaction.getWeek());
    assertEquals(calculator, transaction.getCalculator());
    assertFalse(transaction.isCommitted());
  }

  @Test
  void constructorThrowsExceptionWhenShareIsNull() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new TestTransaction(null, 3, calculator));
    assertEquals("share cannot be null", exception.getMessage());
  }

  @Test
  void constructorThrowsExceptionWhenCalculatorIsNull() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new TestTransaction(share, 3, null));
    assertEquals("calculator cannot be null", exception.getMessage());
  }

  @Test
  void constructorThrowsExceptionWhenWeekIsLessThanOne() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new TestTransaction(share, 0, calculator));
    assertEquals("Week cannot be less than 1", exception.getMessage());
  }

  @Test
  void commitMarksTransactionAsCommitted() {
    Player player = new Player("Nicolai", new BigDecimal("1000"), new BigDecimal("1000"));
    transaction.commit(player);
    assertTrue(transaction.isCommitted());
  }

  @Test
  void commitThrowsExceptionWhenPlayerIsNull() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> transaction.commit(null));
    assertEquals("player cannot be null", exception.getMessage());
  }

  @Test
  void commitThrowsExceptionWhenTransactionAlreadyCommitted() {
    Player player = new Player("Nicolai", new BigDecimal("1000"), new BigDecimal("1000"));
    transaction.commit(player);
    IllegalStateException exception = assertThrows(IllegalStateException.class,
        () -> transaction.commit(player));
    assertEquals("Transaction already committed", exception.getMessage());
  }
}
