package pattern.structure.decorator;

public class Flower extends Change {

    public Flower(GreatSage sage) {
        super(sage);
    }

    @Override
    public void move() {
        System.out.println("Flower Move");
    }
}
