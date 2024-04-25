import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends JFrame {
    private JTextArea serverLog;
    private Server server;
    private List<Message> messages;

    public ChatServer() {
        initGui();
        initServer();
    }

    private void initServer() {
        server = new Server();
        Kryo kryo = server.getKryo();
        kryo.register(Message.class);
        server.start();
        messages = new ArrayList<Message>();
        try {
            server.bind(1234);
            serverLog.append("Server started\n");
        } catch (IOException e) {
            serverLog.append("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }

        server.addListener(new Listener(){
            @Override
            public void connected(Connection connection) {
                super.connected(connection);
                for (Message m : messages) {
                    connection.sendTCP(m);
                }
                serverLog.append("New Connection. Total : " + server.getConnections().length + "\n");
            }

            @Override
            public void disconnected(Connection connection) {
                super.disconnected(connection);
                serverLog.append("Client Disconnected. Total : " + server.getConnections().length + "\n");
            }

            @Override
            public void received(Connection connection, Object o) {
                super.received(connection, o);
                if (o instanceof Message) {
                    Message msg = (Message) o;
                    serverLog.append(msg.author + " : " + msg.text + "\n");
                    messages.add(msg);
                    for (Connection c : server.getConnections()) {
                        c.sendTCP(msg);
                    }
                }
            }
        });
    }

    private void initGui() {
        serverLog = new JTextArea("");
        serverLog.setPreferredSize(new Dimension(600, 600));
        serverLog.setBackground(Color.black);
        serverLog.setForeground(Color.white);
        serverLog.setEditable(false);
        add(serverLog);
        pack();
        setVisible(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ChatServer();
    }
}
