package pattern.create.factory;

public class Strawberry extends Fruit {

    @Override
    public void grow() {
        log("Strawberry grow()");
    }
    @Override
    public void harvest() {
        log("Strawberry harvest()");
    }
    @Override
    public void plant() {
        log("Strawberry plant()");
    }
    private void log(String msg) {
        System.out.println(msg);
    }
}
