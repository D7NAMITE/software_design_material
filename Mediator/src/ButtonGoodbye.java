import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonGoodbye extends JButton {
    private AppController appController;
    public ButtonGoodbye() {
        setText("Goodbye");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appController.goodbye();
            }
        });
    }
}
