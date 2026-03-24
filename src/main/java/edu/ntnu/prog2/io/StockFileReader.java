package edu.ntnu.prog2.io;

import edu.ntnu.prog2.model.Stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StockFileReader {

  public static List<Stock> readStocksFromFile(String filePath) throws IOException {
    List<Stock> stocks = new ArrayList<>();

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
      String line;

      while ((line = bufferedReader.readLine()) != null) {

        line = line.trim();

        if (line.isEmpty()) continue;
        if (line.startsWith("#")) continue;

        String[] parts = line.split(",");

        if (parts.length != 3) {
          System.out.println("Invalid line: " + line);
          continue;
        }

        String symbol = parts[0].trim();
        String name = parts[1].trim();
        BigDecimal price = new BigDecimal(parts[2].trim());

        Stock stock = new Stock(symbol, name, price);
        stocks.add(stock);
      }
    }
    return stocks;
  }
}