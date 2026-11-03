package task3;

/** Bridge Implementor: physical transmission channel. */
public interface MessageChannel {
    void transmit(String recipient, String message);
}
