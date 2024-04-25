public class Main {
    public static void main(String[] args) {
        TextWriter tw = new TextWriter(new FormatNotthing());
        tw.writeText("Hello, world!");

    }
}