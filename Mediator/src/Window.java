import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private ButtonHello bHello = new ButtonHello();
    private ButtonGoodbye bGoodbye = new ButtonGoodbye();
    private Label label = new Label();
    private AppController appController;

    public Window() {
        setLayout(new FlowLayout());
        add(label);
        add(bHello);
        add(bGoodbye);
        pack();
        appController.setbHello(bHello);
        appController.setbGoodbye(bGoodbye);
        appController.setLabel(label);
    }


    public static void main(String[] args) {
        Window window = new Window();
        window.setVisible(true);

    }
}