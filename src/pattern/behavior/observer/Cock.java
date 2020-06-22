package pattern.behavior.observer;

import java.util.Observer;
import java.util.Observable;

public class Cock implements Observer {
    private Sun sun;

    public Cock(Sun sun) {
        this.sun = sun;
        // 将观察者Cock注册到"被观察者sun"上。
        sun.addObserver(this);
    }

    // "被观察者"发生变化时，"观察者"对应的响应方法。
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Cock gogoda,gogoda,gogoda...");
    }
}
