import java.io.IOException;
import java.net.ServerSocket;

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
            EchoServer echoServer = new EchoServer(serverSocket);
            echoServer.start();
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " +
                    portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
