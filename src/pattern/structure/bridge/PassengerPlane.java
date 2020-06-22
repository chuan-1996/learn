package pattern.structure.bridge;

public class PassengerPlane extends Airplane {
    public PassengerPlane(AirplaneMaker maker) {
        super(maker);
    }

    @Override
    public void fly() {
        super.fly();
        System.out.println("PassengerPlane fly.");
    }
}
