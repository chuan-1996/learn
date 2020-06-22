package pattern.structure.bridge;

abstract public class Airplane {
    // 飞机制造商
    protected AirplaneMaker maker;

    // 指定飞机制造商
    public Airplane(AirplaneMaker maker) {
        this.maker = maker;
    }

    public void fly() {
        // 调用AirplaneMaker的produce()方法
        maker.produce();
    }
}
