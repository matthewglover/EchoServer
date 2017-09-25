import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyEchoServer {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
        ) {
            ObservableServerSocket observableServerSocket = new ObservableServerSocket(serverSocket);
            observableServerSocket.pipeTo(observableServerSocket);
            observableServerSocket.readInput();
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " +
                    portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
