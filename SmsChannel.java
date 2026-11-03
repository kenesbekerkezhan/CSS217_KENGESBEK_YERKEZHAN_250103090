package task3;

public class SmsChannel implements MessageChannel {
    @Override
    public void transmit(String recipient, String message) {
        // Condense multi-line content into a single SMS line
        String condensed = message.replace("\n", " -- ");
        System.out.printf("[SMS PROTOCOL -> %s] %s%n", recipient, condensed);
    }
}
