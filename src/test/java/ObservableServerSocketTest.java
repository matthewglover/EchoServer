import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ObservableServerSocketTest {

    private ServerSocket serverSocket;
    private String testData;
    private ByteArrayOutputStream byteArrayOutputStream;
    private TestObserver testObserver;

    @Before
    public void setUp() throws Exception {
        testData = "Test input!";
        testObserver = new TestObserver();

        Socket socket = mock(Socket.class);
        byteArrayOutputStream = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);

        InputStream inputStream = new ByteArrayInputStream(testData.getBytes("UTF-8"));
        when(socket.getInputStream()).thenReturn(inputStream);

        serverSocket = mock(ServerSocket.class);
        when(serverSocket.accept()).thenReturn(socket);
    }

    @Test
    public void forwardsDataToSocketOutput() {
        ObservableServerSocket observableServerSocket = new ObservableServerSocket(serverSocket);
        observableServerSocket.onData(testData);
        assertEquals(testData, byteArrayOutputStream.toString().trim());
    }

    @Test
    public void emitsDataFromSocketInput() {
        ObservableServerSocket observableServerSocket = new ObservableServerSocket(serverSocket);
        observableServerSocket.pipeTo(testObserver);
        observableServerSocket.readInput();
        assertEquals(testData, testObserver.receivedData);
    }
}