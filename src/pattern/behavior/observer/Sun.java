package pattern.behavior.observer;

import java.util.Observable;

public class Sun extends Observable {

    public void rise() {
        System.out.println("Sun rise.");
        // 设置"被观察者"的状态标记，表示它发生了变化。
        this.setChanged();
        // 通知"观察者"该变化。
        this.notifyObservers();
    }
}
