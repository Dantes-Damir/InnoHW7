import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by admin on 17.04.2017.
 */
public class SocketReader implements Runnable {
    private Socket socket;
    private ChatClient chatClient;
    private BufferedReader socketReader;

    public SocketReader(Socket socket, ChatClient chatClient) {
        this.socket = socket;
        this.chatClient = chatClient;
        try {
            this.socketReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            String msg = null;
            try {
                msg = socketReader.readLine();
            } catch (IOException e) {
                if ("Сокет закрыт".equals(e.getMessage())) {
                    break;
                }
                System.out.println("Соединение потеряно");
                chatClient.close();
            }
            if (msg == null) {
                System.out.println("Серевер прервал соединение");
                chatClient.close();
            } else {
                System.out.println(msg);
            }
        }
    }
}