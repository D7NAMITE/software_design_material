public class AppController {
    private ButtonHello bHello;
    private ButtonGoodbye bGoodbye;
    private Label label;

    public void hello() {
        bHello.setEnabled(false);
        bGoodbye.setEnabled(true);
        label.setText("Hello");
    }

    public void goodbye() {
        bHello.setEnabled(true);
        bGoodbye.setEnabled(false);
        label.setText("Goodbye");
    }

    public void setbHello(ButtonHello bHello) {
        this.bHello = bHello;
    }

    public void setbGoodbye(ButtonGoodbye bGoodbye) {
        this.bGoodbye = bGoodbye;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
