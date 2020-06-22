package pattern.structure.decorator;

public class Insect extends Change {

    public Insect(GreatSage sage) {
        super(sage);
    }

    @Override
    public void move() {
        System.out.println("Insect Move");
    }
}
