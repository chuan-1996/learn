package pattern.create.factory;

public class Grape extends Fruit {
    @Override
    public void grow() {
        System.out.println("Grape grow()");
    }
    @Override
    public void harvest() {
        System.out.println("Grape harvest()");
    }
    @Override
    public void plant() {
        System.out.println("Grape plant()");
    }
}
