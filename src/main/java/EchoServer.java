import java.io.IOException;
import java.net.ServerSocket;

public class EchoServer {

    private final ObservableServerSocket observableServerSocket;

    public EchoServer(ServerSocket serverSocket) throws IOException {
        observableServerSocket = new ObservableServerSocket(serverSocket);
    }

    public void start() throws IOException {
        setupDataPipeLine();
        readFromSocketInput();
    }

    private void readFromSocketInput() throws IOException {
        observableServerSocket.readInput();
    }

    private void setupDataPipeLine() {
        observableServerSocket
                .pipeTo(observableServerSocket);
    }
}
