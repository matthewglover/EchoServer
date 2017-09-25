import java.util.Observable;
import java.util.Observer;

public abstract class Pipeable extends Observable implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        onData((String) arg);
    }

    public void onData(String data) {
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
