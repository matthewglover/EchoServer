import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ObservableToOutputStreamTest {
    private String testData ;
    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream printStream;
    private ObservableToOutputStream observableToOutputStream;

    @Before
    public void setUp() throws Exception {
       testData = "Test input!";
       byteArrayOutputStream = new ByteArrayOutputStream();
       printStream = new PrintStream(byteArrayOutputStream);
       observableToOutputStream = new ObservableToOutputStream(printStream);
    }

    @Test
    public void writesReceivedDataToOutputStream() {
        observableToOutputStream.onData(testData);
        assertEquals(testData, byteArrayOutputStream.toString().trim());
    }
}