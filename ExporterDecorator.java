package task1;

/**
 * Base Decorator.
 *
 * The stack is read as a processing pipeline: the decorator closest to the
 * base exporter transforms the payload first, the outermost one transforms it
 * last, and the base exporter writes the final result. So
 * Base64(HeaderFooter(BaseFileExporter)) means "add header/footer, then
 * Base64-encode, then write to disk" (as required by the verification trace).
 */
public abstract class ExporterDecorator implements Exporter {
    protected final Exporter wrappedExporter;

    public ExporterDecorator(Exporter wrappedExporter) {
        if (wrappedExporter == null) {
            throw new IllegalArgumentException("Wrapped exporter cannot be null");
        }
        this.wrappedExporter = wrappedExporter;
    }

    @Override
    public void export(String destination, String data) {
        terminalExporter().export(destination, applyTransforms(data));
    }

    /** This decorator's own transformation of the payload. */
    protected abstract String transform(String data);

    /** Applies inner decorators' transforms first, then this one. */
    protected String applyTransforms(String data) {
        String inner = (wrappedExporter instanceof ExporterDecorator)
                ? ((ExporterDecorator) wrappedExporter).applyTransforms(data)
                : data;
        return transform(inner);
    }

    /** The concrete (non-decorator) exporter at the bottom of the stack. */
    protected Exporter terminalExporter() {
        return (wrappedExporter instanceof ExporterDecorator)
                ? ((ExporterDecorator) wrappedExporter).terminalExporter()
                : wrappedExporter;
    }
}
