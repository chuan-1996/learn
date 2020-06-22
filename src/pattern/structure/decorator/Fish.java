package pattern.structure.decorator;

public class Fish extends Change {

    public Fish(GreatSage sage) {
        super(sage);
    }

    @Override
    public void move() {
        System.out.println("Fish Move");
    }
}
