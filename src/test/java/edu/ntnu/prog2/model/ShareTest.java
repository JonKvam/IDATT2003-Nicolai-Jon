package edu.ntnu.prog2.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

class ShareTest {
    private Share share;
    private Stock stock;
    private final BigDecimal QUANTITY = new BigDecimal("10");
    private final BigDecimal PURCHASE_PRICE = new BigDecimal("60");

    @BeforeEach
    void setUp() {
        stock = new Stock("NVDA", "Nvidia", new BigDecimal("150"));
        share = new Share(stock, QUANTITY, PURCHASE_PRICE);
    }

    @Test
    public void testGetStock() {
        assertEquals(stock, share.getStock());
    }

    @Test
    public void testGetQuantity() {
        assertEquals(QUANTITY, share.getQuantity());
    }

    @Test
    public void testGetPurchasePrice() {
        assertEquals(PURCHASE_PRICE, share.getPurchasePrice());
    }
}