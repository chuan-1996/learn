package pattern.structure.decorator;

/**
 * 具体构件
 */
public class Monkey extends GreatSage {
    @Override
    public void move() {
        System.out.println("Monkey Move");
    }
}
