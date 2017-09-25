import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyEchoClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(hostName, portNumber)) {
            EchoClient echoClient = new EchoClient(socket, System.in, System.out);
            echoClient.start();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}
