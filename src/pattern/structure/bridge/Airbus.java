package pattern.structure.bridge;

public class Airbus extends AirplaneMaker {
    @Override
    public void produce() {
        System.out.println("Airbus produced.");
    }
}
