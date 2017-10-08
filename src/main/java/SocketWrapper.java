import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketWrapper {
    private PrintWriter realSocketOut;
    private BufferedReader realSocketIn;

    public SocketWrapper(Socket realSocket) throws IOException {
        realSocketOut = new PrintWriter(realSocket.getOutputStream(), true);
        realSocketIn = new BufferedReader(new InputStreamReader(realSocket.getInputStream()));
    }

    protected void writeToSocket(String data) {
        realSocketOut.println(data);
    }

    public String readFromSocket() throws IOException {
        return realSocketIn.readLine();
    }
}
