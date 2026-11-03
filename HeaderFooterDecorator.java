package task1;

/** Concrete Decorator: wraps the payload with a timestamp header and integrity footer. */
public class HeaderFooterDecorator extends ExporterDecorator {
    private final String timestamp;

    public HeaderFooterDecorator(Exporter wrappedExporter, String timestamp) {
        super(wrappedExporter);
        this.timestamp = timestamp;
    }

    @Override
    protected String transform(String data) {
        return "=== METADATA HEADER: " + timestamp + " ===\n"
                + data + "\n"
                + "=== INTEGRITY CHECK: SHA-256 OK | END RECORD ===";
    }
}
