package task3;

/** Bridge Abstraction: what kind of alert, independent of how it is transmitted. */
public abstract class Alert {
    protected final MessageChannel channel;    // Bridge link to Implementor
    protected final DispatchStrategy strategy; // Strategy link

    public Alert(MessageChannel channel, DispatchStrategy strategy) {
        if (channel == null || strategy == null) throw new IllegalArgumentException("Dependencies cannot be null");
        this.channel = channel;
        this.strategy = strategy;
    }

    public void processAlert(String recipient, String rawContent, int currentHour) {
        int severity = getSeverityLevel();
        if (strategy.shouldDispatch(severity, currentHour)) {
            channel.transmit(recipient, formatAlertBody(rawContent));
        } else {
            System.out.printf("[DISPATCH SUPPRESSED] Alert blocked by policy '%s' at hour %02d:00 (Severity: %d)%n",
                    strategy.getStrategyName(), currentHour, severity);
        }
    }

    /** Alias matching the method name in the specification. */
    public void send(String recipient, String message, int severity, int hour) {
        processAlert(recipient, message, hour);
    }

    public abstract int getSeverityLevel();

    public abstract String formatAlertBody(String raw);
}
