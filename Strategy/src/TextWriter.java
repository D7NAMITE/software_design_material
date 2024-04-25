public class TextWriter {
    private TextFormatter formatter;

    public TextWriter(TextFormatter formatter) {
        this.formatter = formatter;
    }

    public void setFormatter(TextFormatter formatter) {
        this.formatter = formatter;
    }

    public void writeText(String text) {
        System.out.println(formatter.format(text));
    }
}
