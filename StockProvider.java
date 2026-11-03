package task2;

/** Target interface expected by the dashboard. */
public interface StockProvider {
    double getMarketPrice(String ticker);
    String getStatus(String ticker);
}
