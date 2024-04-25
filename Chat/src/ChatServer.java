import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private int port = 12345;
    private List<PrintWriter> clientWriters = new ArrayList<>();

    public void start() throws IOException {
        System.out.println("Start server on port: " + port);
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket clientSocket = serverSocket.accept(); // wait fot client to connect
            System.out.println("New client connected");
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            clientWriters.add(out);
            broadcast("A new client connected");

            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream())
                    );
                    String fromClient;
                    while (true) {
                        fromClient = in.readLine();
                        if (fromClient != null) {
                            System.out.println(fromClient);
                            broadcast(fromClient);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    public void broadcast(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }

    public static void main(String[] args) {
        try {
            new ChatServer().start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
