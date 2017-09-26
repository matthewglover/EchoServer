import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public abstract class Pipeable extends Observable implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        try {
            onData((String) arg);
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public void onData(String data) throws IOException {
    }

    public void emitData(String data) {
        setChanged();
        notifyObservers(data);
    }

    public Pipeable pipeTo(Pipeable receiver) {
        addObserver(receiver);
        return receiver;
    }
}
