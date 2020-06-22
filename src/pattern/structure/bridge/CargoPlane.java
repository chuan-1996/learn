package pattern.structure.bridge;

public class CargoPlane extends Airplane {

    public CargoPlane(AirplaneMaker maker) {
        super(maker);
    }

    @Override
    public void fly() {
        super.fly();
        System.out.println("CargoPlane fly.");
    }
}
