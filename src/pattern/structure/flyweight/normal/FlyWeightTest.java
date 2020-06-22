package pattern.structure.flyweight.normal;

/**
 * @author qq491
 * string就是一个典型的享元模式
 */
public class FlyWeightTest {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fly = factory.factory(new Character('a'));
        fly.operation("First Call");

        fly = factory.factory(new Character('b'));
        fly.operation("Second Call");

        fly = factory.factory(new Character('a'));
        fly.operation("Third Call");
    }
}
