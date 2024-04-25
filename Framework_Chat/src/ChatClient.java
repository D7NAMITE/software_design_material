import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ChatClient extends JFrame {
    private JTextArea messagesPanel;
    private JTextArea authorPanel;
    private JTextField input;
    private JTextField authorInput;
    private Client client;

    public ChatClient() {
        initGui();
        initClient();
    }

    private void initClient() {
        client = new Client();
        Kryo kryo = client.getKryo();
        kryo.register(Message.class);
        client.start();
        try {
            client.connect(5000, "localhost", 1234);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object o) {
                super.received(connection, o);
                if (o instanceof Message) {
                    Message msg = (Message) o;
                    messagesPanel.append(msg.author + " : " + msg.text + "\n");
                }
            }
        });
    }

    private void initGui() {
        setLayout(new BorderLayout());
        authorPanel = new JTextArea();
        authorPanel.setPreferredSize(new Dimension(320, 320));
        authorPanel.setEditable(false);
        add(authorPanel, BorderLayout.CENTER);

        authorInput = new JTextField("");
        authorInput.setPreferredSize(new Dimension(320, 30));
        add(authorInput, BorderLayout.NORTH);
        pack();


        messagesPanel = new JTextArea("");
        messagesPanel.setPreferredSize(new Dimension(320, 320));
        messagesPanel.setEditable(false);
        add(messagesPanel, BorderLayout.CENTER);

        input = new JTextField("");
        input.setPreferredSize(new Dimension(320, 30));
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        add(input, BorderLayout.SOUTH);
        pack();

        setVisible(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void sendMessage() {
        Message msg = new Message();
        msg.text = input.getText();
        msg.author = authorInput.getText();
        client.sendTCP(msg);
        input.setText("");
    }

    public static void main(String[] args) {
        new ChatClient();
    }
}
