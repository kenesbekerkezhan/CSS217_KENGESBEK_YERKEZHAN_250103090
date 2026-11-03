package task1;

/** Strategy: interchangeable report formatting algorithms. */
public interface ReportFormatter {
    String format(String title, String[] headers, String[][] rows);
}
