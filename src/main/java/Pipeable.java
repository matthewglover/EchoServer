import java.util.Observable;
import java.util.Observer;

public abstract class Pipeable extends Observable implements Observer {
    public abstract void onData(String data);

    @Override
    public void update(Observable o, Object arg) {
        onData((String) arg);
    }

    public Pipeable pipeTo(Pipeable receiver) {
        addObserver(receiver);
        return receiver;
    }
}
