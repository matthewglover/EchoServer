import java.io.IOException;
import java.net.Socket;

public class ObservableSocket extends Pipeable {

    private final SocketWrapper socketWrapper;

    public ObservableSocket(Socket realSocket) throws IOException {
        this.socketWrapper = new SocketWrapper(realSocket);
    }
    public void onData(String data) throws IOException {
        socketWrapper.writeToSocket(data);
        listenForSocketInput();
    }

    private void listenForSocketInput() throws IOException {
        String data = socketWrapper.readFromSocket();
        emitData(data);
    }
}
