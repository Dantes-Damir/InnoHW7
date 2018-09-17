

public class Client {
    public static void main(String[] args) {
        ChatClient client = new ChatClient("192.168.1.129", Main.port);
        client.startChat();
    }
}