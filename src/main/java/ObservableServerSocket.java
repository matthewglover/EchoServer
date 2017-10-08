import java.io.IOException;
import java.net.ServerSocket;

public class ObservableServerSocket extends Pipeable {


    private final SocketWrapper socketWrapper;

    public ObservableServerSocket(ServerSocket serverSocket) throws IOException {
        this.socketWrapper = new SocketWrapper(serverSocket.accept());
    }

    @Override
    public void onData(String data) {
        socketWrapper.writeToSocket(data);
    }

    public void readInput() throws IOException {
        while (true) {
            String data = socketWrapper.readFromSocket();
            if (data == null) {
                break;
            } else {
                emitData(data);
            }
        }
    }

}
