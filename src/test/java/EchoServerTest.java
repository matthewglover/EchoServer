import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EchoServerTest {

    private String testData;
    private ByteArrayOutputStream mockSocketOutputStream;
    private ByteArrayInputStream mockSocketInputStream;
    private ServerSocket mockServerSocket;

    @Before
    public void setUp() throws Exception {
        testData = "Test Input!";

        Socket mockSocket = mock(Socket.class);
        mockSocketOutputStream = new ByteArrayOutputStream();
        mockSocketInputStream = new ByteArrayInputStream(testData.getBytes("UTF-8"));

        when(mockSocket.getOutputStream()).thenReturn(mockSocketOutputStream);
        when(mockSocket.getInputStream()).thenReturn(mockSocketInputStream);

        mockServerSocket = mock(ServerSocket.class);
        when(mockServerSocket.accept()).thenReturn(mockSocket);
    }

    @Test
    public void writesSocketInputToSocketOutput() throws IOException {
        EchoServer echoServer = new EchoServer(mockServerSocket);
        echoServer.start();
        assertEquals(testData, mockSocketOutputStream.toString().trim());
    }
}