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
        while (true) {
            String data = readLine();
            if (data == null) {
                break;
            } else {
                emitData(data);
            }
        }
    }

    private String readLine() {
        try {
            return streamReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
