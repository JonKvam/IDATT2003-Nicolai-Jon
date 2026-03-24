package edu.ntnu.prog2.io;

import edu.ntnu.prog2.model.Stock;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StockFileReaderTest {
  private File createTempFile(String content) throws Exception {
    File file = File.createTempFile("Stock", ".csv");
    FileWriter writer = new FileWriter(file);
    writer.write(content);
    writer.close();
    return file;
  }

  @Test
  void testReadStockFile() throws Exception {
    String data = """
            NVDA,Nvidia,191.27
            AAPL,Apple Inc.,276.43
            """;
    File file = createTempFile(data);
    List<Stock> stocks = StockFileReader.readStocksFromFile(file.getAbsolutePath());

    assertEquals(2, stocks.size());
    assertEquals("NVDA", stocks.getFirst().getSymbol());
    assertEquals(new BigDecimal("191.27"), stocks.getFirst().getSalesPrice());
  }

  @Test
  void testReadStockFileIgnoreComments() throws Exception {
    String data = """
                # This is a comment
                NVDA,Nvidia,191.27
                # Another comment
                """;
    File file = createTempFile(data);
    List<Stock> stocks = StockFileReader.readStocksFromFile(file.getAbsolutePath());

    assertEquals(1, stocks.size());
    assertEquals("NVDA", stocks.getFirst().getSymbol());
  }

  @Test
  void testReadStockFileIgnoreEmptyLines() throws Exception {
    String data = """
                
                NVDA,Nvidia,191.27
                
                """;
    File file = createTempFile(data);
    List<Stock> stocks = StockFileReader.readStocksFromFile(file.getAbsolutePath());

    assertEquals(1, stocks.size());
    assertEquals("NVDA", stocks.getFirst().getSymbol());
  }
}