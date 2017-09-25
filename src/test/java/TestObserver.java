public class TestObserver extends Pipeable {

    public String receivedData;

    @Override
    public void onData(String data) {
        receivedData = data;
    }
}
