package pattern.structure.decorator;

/**
 * 装饰
 */
public class Change extends GreatSage {
    private GreatSage sage;

    public Change(GreatSage sage){
        this.sage = sage;
    }

    @Override
    public void move() {
        sage.move();
    }
}
