package pattern.behavior.observer;

/**
 * 　Observer是一个接口。它只定义了一个方法，即update()方法，当被观察者对象的状态发生变化时，被观察者对象的notifyObservers()方法就会调用这一方法。
 */
public class ObserverTest {
    private static Cock cock;
    private static Sun sun;
    public static void main(String[] args) {
        // 新建"太阳"(被观察者)
        sun = new Sun();
        // 新建"公鸡"(观察者)
        cock = new Cock(sun);
        // 太阳升起。公鸡观察到太阳升级后，会打鸣！
        sun.rise();
    }
}
