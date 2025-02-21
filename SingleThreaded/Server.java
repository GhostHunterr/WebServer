
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() throws IOException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        while (true) {
            System.out.println("Server is Listening on port: " + port);
            try (Socket acceptedConnection = socket.accept()) {
                System.out.println("Connection accepted from client " + acceptedConnection.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                toClient.println("Hello From the Single-Threaded Server!");

                acceptedConnection.close();
                toClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
