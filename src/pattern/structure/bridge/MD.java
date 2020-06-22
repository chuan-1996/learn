package pattern.structure.bridge;

public class MD extends AirplaneMaker {
    @Override
    public void produce() {
        System.out.println("MD produced.");
    }
}
