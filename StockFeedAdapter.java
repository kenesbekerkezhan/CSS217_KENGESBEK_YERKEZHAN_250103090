package task2;

import java.util.HashMap;
import java.util.Map;

/** Adapter: converts LegacyStockFeed packets into the StockProvider interface. */
public class StockFeedAdapter implements StockProvider {
    private final LegacyStockFeed legacyFeed;

    public StockFeedAdapter(LegacyStockFeed legacyFeed) {
        if (legacyFeed == null) throw new IllegalArgumentException("Legacy feed cannot be null");
        this.legacyFeed = legacyFeed;
    }

    private Map<String, String> parsePacket(String raw) {
        Map<String, String> map = new HashMap<>();
        if (raw == null) return map;
        for (String token : raw.split(";")) {
            String[] kv = token.split("=", 2);
            if (kv.length == 2) map.put(kv[0].trim(), kv[1].trim());
        }
        return map;
    }

    @Override
    public double getMarketPrice(String ticker) {
        Map<String, String> parsed = parsePacket(legacyFeed.fetchRawTickerPacket(ticker));
        double bid = Double.parseDouble(parsed.getOrDefault("BID", "0.0"));
        double ask = Double.parseDouble(parsed.getOrDefault("ASK", "0.0"));
        return (bid + ask) / 2.0;
    }

    @Override
    public String getStatus(String ticker) {
        return parsePacket(legacyFeed.fetchRawTickerPacket(ticker)).getOrDefault("STATUS", "UNAVAILABLE");
    }
}
