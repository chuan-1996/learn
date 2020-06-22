package pattern.structure.facade;

public class Sensor {
    // 启动感应器
    public void activate() {
        System.out.println("Activating on the sensor.");
    }
    // 关闭感应器
    public void deactivate() {
        System.out.println("Deactivating the sensor.");
    }
    // 触发感应器
    public void trigger() {
        System.out.println("The sensor has been triggered.");
    }
}
