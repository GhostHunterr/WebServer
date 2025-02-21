
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer() {
        return (clientSocket) -> {
            try (
                    PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true); 
                    BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {
                toClient.println("Hello from the Server.");
                String line = fromClient.readLine();
                System.out.println("Response form Client is : " + line);
                clientSocket.close();
                toClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        int port = 8010;
        Server server = new Server();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server Listening on port: " + port);
            while (true) {
                Socket acceptedSocket = serverSocket.accept();
                Thread thread = new Thread(() -> server.getConsumer().accept(acceptedSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
