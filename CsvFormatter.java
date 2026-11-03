package task1;

/** Concrete Strategy: formats the report as quoted, comma-delimited rows. */
public class CsvFormatter implements ReportFormatter {
    @Override
    public String format(String title, String[] headers, String[][] rows) {
        StringBuilder sb = new StringBuilder();
        sb.append("# TITLE: ").append(title).append("\n");
        sb.append(joinQuoted(headers)).append("\n");
        for (String[] row : rows) {
            sb.append(joinQuoted(row)).append("\n");
        }
        return sb.toString();
    }

    private static String joinQuoted(String[] values) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            String v = values[i] == null ? "" : values[i];
            sb.append('"').append(v.replace("\"", "\"\"")).append('"');
            if (i < values.length - 1) sb.append(',');
        }
        return sb.toString();
    }
}
