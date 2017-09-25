import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class ObservableInputStreamTest {

    private String testData;
    private InputStream inputStream;
    private ObservableInputStream observableInputStream;
    private TestObserver testObserver;

    @Before
    public void setUp() throws Exception {
        testData = "Test Input!";
        testObserver = new TestObserver();
        inputStream = new ByteArrayInputStream(testData.getBytes("UTF-8"));
        observableInputStream = new ObservableInputStream(inputStream);
    }

    @Test
    public void forwardsInput() throws Exception {
        observableInputStream.pipeTo(testObserver);
        observableInputStream.read();
        assertEquals(testObserver.receivedData, testData);
    }
}