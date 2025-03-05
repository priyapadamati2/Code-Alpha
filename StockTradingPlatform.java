import java.util.*;

class Stock {
    String name;
    double price;

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Portfolio {
    private final Map<String, Integer> holdings = new HashMap<>();
    private double balance;

    public Portfolio(double balance) {
        this.balance = balance;
    }

    public void buyStock(String stockName, int quantity, double price) {
        double cost = quantity * price;
        if (cost > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= cost;
            holdings.put(stockName, holdings.getOrDefault(stockName, 0) + quantity);
            System.out.println("Bought " + quantity + " shares of " + stockName);
        }
    }

    public void sellStock(String stockName, int quantity, double price) {
        if (!holdings.containsKey(stockName) || holdings.get(stockName) < quantity) {
            System.out.println("Not enough shares to sell!");
        } else {
            holdings.put(stockName, holdings.get(stockName) - quantity);
            balance += quantity * price;
            System.out.println("Sold " + quantity + " shares of " + stockName);
        }
    }

    public void displayPortfolio() {
        System.out.println("Portfolio:");
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + " shares");
        }
        System.out.println("Balance: $" + balance);
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Stock> market = new HashMap<>();
        market.put("AAPL", new Stock("Apple", 150.0));
        market.put("GOOGL", new Stock("Google", 2800.0));
        market.put("TSLA", new Stock("Tesla", 900.0));

        Portfolio portfolio = new Portfolio(10000);
        
        while (true) {
            System.out.println("1. Buy Stock\n2. Sell Stock\n3. View Portfolio\n4. Exit");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.println("Enter stock symbol and quantity to buy:");
                    String buySymbol = scanner.next();
                    int buyQuantity = scanner.nextInt();
                    if (market.containsKey(buySymbol)) {
                        portfolio.buyStock(buySymbol, buyQuantity, market.get(buySymbol).price);
                    } else {
                        System.out.println("Stock not found!");
                    }
                    break;
                case 2:
                    System.out.println("Enter stock symbol and quantity to sell:");
                    String sellSymbol = scanner.next();
                    int sellQuantity = scanner.nextInt();
                    if (market.containsKey(sellSymbol)) {
                        portfolio.sellStock(sellSymbol, sellQuantity, market.get(sellSymbol).price);
                    } else {
                        System.out.println("Stock not found!");
                    }
                    break;
                case 3:
                    portfolio.displayPortfolio();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
