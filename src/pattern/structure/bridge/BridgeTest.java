package pattern.structure.bridge;

public class BridgeTest {
    public static void main(String[] args) {
        // "飞机制造商"为Airbus
        AirplaneMaker maker = new Airbus();
        // "飞机类型"为PassengerPlane
        Airplane plane = new PassengerPlane(maker);
        // 飞机飞行
        plane.fly();

        // "飞机制造商"为MD
        maker = new MD();
        // "飞机类型"为CargoPlane
        plane = new CargoPlane(maker);
        // 飞机飞行
        plane.fly();
    }
}
