import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHello extends JButton {
    private AppController appController;
    public ButtonHello() {
        setText("Hello");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appController.hello();
            }
        });
    }
}
