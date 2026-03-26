package edu.ntnu.prog2.io;

import edu.ntnu.prog2.model.Stock;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StockFileWriterTest {
  private String readFile(File file) throws IOException {
    return Files.readString(file.toPath());
  }

  @Test
    void testWriteStockFileForSingleStock() throws IOException {
    File file = File.createTempFile("Stocks", ".csv");

    Stock stock = new Stock("AAPL", "Apple", new BigDecimal("250.50"));

    StockFileWriter.writeStocksToFile(file.getAbsolutePath(), List.of(stock));
    String content = readFile(file);

    assertTrue(content.contains("AAPL,Apple,250.50"));
  }

  @Test
    void testWriteStockFileForMultipleStocks() throws IOException {
    File file = File.createTempFile("Stocks", ".csv");

    List<Stock> stocks = List.of(
            new Stock("AAPL", "Apple", new BigDecimal("250.50")),
            new Stock("NVDA", "Nvidia", new BigDecimal("150.67"))
            );

    StockFileWriter.writeStocksToFile(file.getAbsolutePath(), stocks);
    String content = readFile(file);

    assertTrue(content.contains("AAPL,Apple,250.50"));
    assertTrue(content.contains("NVDA,Nvidia,150.67"));
  }
}