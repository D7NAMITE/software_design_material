public class FormatSpace implements TextFormatter{
    @Override
    public String format(String input) {
        return String.join(" ", input.split(""));
    }
}
