import java.util.function.Function;

public class ObservableTransform extends Pipeable {

    private final Function<String, String> transformer;

    public ObservableTransform(Function<String, String> transformer) {
        super();
        this.transformer = transformer;
    }

    @Override
    public void onData(String data) {
        emitData(transformer.apply(data));
    }
}
