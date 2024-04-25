import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.ServerSocket;

public class ChatClient {
    private String address = "localhost";
    private int port = 12345;

    public void start() throws IOException {
        Socket socket = new Socket(address, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        new Thread(() -> {
            String fromServer;
            while (true) {
                try {
                    fromServer = in.readLine();
                    if (fromServer != null) {
                        System.out.println(fromServer);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader userInput = new BufferedReader(
                new InputStreamReader(System.in));

        String fromUser;
        while (true) {
            fromUser = userInput.readLine();
            if(fromUser != null) {
                out.println(fromUser);
            }
        }

    }

    public static void main(String[] args) {
        try {
            new ChatClient().start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
