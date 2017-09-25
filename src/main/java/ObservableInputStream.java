import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ObservableInputStream extends Pipeable {

    private final BufferedReader streamReader;

    public ObservableInputStream(InputStream inputStream) {
        streamReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void read() {
        String data;
        try {
            while ((data = streamReader.readLine()) != null) {
                emitData(data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
