import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

public class EchoClient {

    private final ObservableInputStream observableInputStream;
    private final ObservableToOutputStream observableToOutputStream;
    private final ObservableSocket observableSocket;
    private final ObservableTransform observableTransform = new ObservableTransform(str -> "Echo: " + str);

    public EchoClient(Socket socket, InputStream inputStream, PrintStream outputStream) throws IOException {
        observableInputStream = new ObservableInputStream(inputStream);
        observableToOutputStream = new ObservableToOutputStream(outputStream);
        observableSocket = new ObservableSocket(socket);
    }

    public void start() {
        setupDataPipeLine();
        readFromInputStream();
    }

    private void setupDataPipeLine() {
        observableInputStream
                .pipeTo(observableSocket)
                .pipeTo(observableTransform)
                .pipeTo(observableToOutputStream);
    }

    private void readFromInputStream() {
        observableInputStream.read();
    }
}
