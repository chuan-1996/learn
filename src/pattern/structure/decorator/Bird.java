package pattern.structure.decorator;

public class Bird extends Change {

    public Bird(GreatSage sage) {
        super(sage);
    }

    @Override
    public void move() {
        System.out.println("Bird Move");
    }
}
