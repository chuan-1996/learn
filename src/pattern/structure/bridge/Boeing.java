package pattern.structure.bridge;

public class Boeing extends AirplaneMaker {
    @Override
    public void produce() {
        System.out.println("Boeing produced.");
    }
}
