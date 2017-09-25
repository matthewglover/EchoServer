import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ObservableSocket extends Pipeable {
    private final PrintWriter realSocketOut;
    private final BufferedReader realSocketIn;

    public ObservableSocket(Socket realSocket) {
        try {
            realSocketOut = new PrintWriter(realSocket.getOutputStream(), true);
            realSocketIn = new BufferedReader(new InputStreamReader(realSocket.getInputStream()));
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void onData(String data) {
        realSocketOut.println(data);
        listenForSocketInput();
    }

    private void listenForSocketInput() {
        try {
            String data = realSocketIn.readLine();
            emitData(data);
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
