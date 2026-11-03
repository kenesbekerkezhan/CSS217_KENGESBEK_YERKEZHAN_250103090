package task3;

/** Strategy: runtime dispatch policy. */
public interface DispatchStrategy {
    boolean shouldDispatch(int severityLevel, int currentHour);
    String getStrategyName();
}
