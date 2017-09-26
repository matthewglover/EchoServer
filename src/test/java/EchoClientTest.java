import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EchoClientTest {

    private String testData;
    private InputStream mockSystemIn;
    private ByteArrayOutputStream mockSystemOutByteStream;
    private PrintStream mockSystemOut;
    private ByteArrayOutputStream mockSocketOutputStream;
    private ByteArrayInputStream mockSocketInputStream;
    private Socket mockSocket;

    @Before
    public void setUp() throws Exception {
        testData = "Test Input!";
        mockSystemIn = new ByteArrayInputStream(testData.getBytes("UTF-8"));
        mockSystemOutByteStream = new ByteArrayOutputStream();
        mockSystemOut = new PrintStream(mockSystemOutByteStream);


        mockSocket = mock(Socket.class);
        mockSocketOutputStream = new ByteArrayOutputStream();
        mockSocketInputStream = new ByteArrayInputStream(testData.getBytes("UTF-8"));

        when(mockSocket.getOutputStream()).thenReturn(mockSocketOutputStream);
        when(mockSocket.getInputStream()).thenReturn(mockSocketInputStream);
    }

    @Test
    public void writesSystemInputToSocketOutput() throws IOException {
        EchoClient echoClient = new EchoClient(mockSocket, mockSystemIn, mockSystemOut);
        echoClient.start();
        assertEquals(testData, mockSocketOutputStream.toString().trim());
    }

    @Test
    public void writesSocketInputToSystemOutput() throws IOException {
        EchoClient echoClient = new EchoClient(mockSocket, mockSystemIn, mockSystemOut);
        echoClient.start();
        assertEquals("Echo: " + testData, mockSystemOutByteStream.toString().trim());
    }
}