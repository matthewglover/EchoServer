import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ObservableSocketTest {

    private String testData;
    private Socket testSocket;
    private ByteArrayOutputStream mockOutputStream;
    private ByteArrayInputStream mockInputStream;
    private TestObserver testObserver;

    @Before
    public void setUp() throws IOException {
        testData = "Test input!";
        testSocket = mock(Socket.class);
        mockOutputStream = new ByteArrayOutputStream();
        mockInputStream = new ByteArrayInputStream(testData.getBytes("UTF-8"));
        testObserver = new TestObserver();

        when(testSocket.getOutputStream()).thenReturn(mockOutputStream);
        when(testSocket.getInputStream()).thenReturn(mockInputStream);
    }

    @Test
    public void writesInputToSocket() throws IOException {
        ObservableSocket mySocket = new ObservableSocket(testSocket);
        mySocket.onData(testData);
        assertEquals(testData, mockOutputStream.toString().trim());
    }

    @Test
    public void onDataWaitsForThenEmitsDataReceivedOnSocketInputStream() throws Exception {
        ObservableSocket mySocket = new ObservableSocket(testSocket);
        mySocket.addObserver(testObserver);
        mySocket.onData(testData);
        assertEquals(testData, testObserver.receivedData);
    }

    @Test
    public void implementsPipeable() throws IOException {
        Pipeable mySocket = new ObservableSocket(testSocket);
        mySocket.pipeTo(testObserver);
        mySocket.onData(testData);
        assertEquals(testData, testObserver.receivedData);
    }
}