import java.io.PrintStream;

public class ObservableToOutputStream extends Pipeable {
    private final PrintStream outputStream;

    public ObservableToOutputStream(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void onData(String data) {
        outputStream.println(data);
    }
}
