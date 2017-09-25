import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ObservableServerSocket extends Pipeable {

    private final PrintWriter realSocketOut;
    private final BufferedReader realSocketIn;


    public ObservableServerSocket(ServerSocket serverSocket) {
        try {
            Socket realSocket = serverSocket.accept();
            realSocketOut = new PrintWriter(realSocket.getOutputStream(), true);
            realSocketIn = new BufferedReader(new InputStreamReader(realSocket.getInputStream()));
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void onData(String data) {
        realSocketOut.println(data);
    }

    public void readInput() {
        String data;
        try {
            while ((data = realSocketIn.readLine()) != null) {
                emitData(data);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
