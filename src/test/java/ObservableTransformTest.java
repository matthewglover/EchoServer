import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObservableTransformTest {

    String testData = "Test input!";
    ObservableTransform transform;
    TestObserver testObserver = new TestObserver();

    @Before
    public void setUp() throws Exception {
        transform = new ObservableTransform(str -> "Echo: " + str);
        testObserver = new TestObserver();
    }

    @Test
    public void appliesTransformToData() {
        transform.pipeTo(testObserver);
        transform.onData(testData);
        assertEquals("Echo: " + testData, testObserver.receivedData);
    }
}