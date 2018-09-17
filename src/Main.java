import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static Integer port = 7777;
    private static BlockingQueue<Server> server = new LinkedBlockingQueue<Server>();
    private static HashSet<String> names = new HashSet<String>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket socket = serverSocket.accept();
                Server socketThread = new Server(socket, server, names);
                Thread thread = new Thread(socketThread);
                thread.setDaemon(true);
                thread.start();
                server.add(socketThread);

                if (server.size() == 0)
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}