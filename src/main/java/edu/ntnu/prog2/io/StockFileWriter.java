package edu.ntnu.prog2.io;

import edu.ntnu.prog2.model.Stock;

import java.io.IOException;
import java.io.FileWriter;
import java.util.List;

public class StockFileWriter {
  public static void writeStocksToFile(String filePath, List<Stock> stocks) throws IOException {
    try (FileWriter writer = new FileWriter(filePath)) {
      for (Stock stock : stocks) {
        writer.write(stock.getSymbol() + "," + stock.getCompany() + "," + stock.getSalesPrice() + "\n");
      }
    }
  }
}
