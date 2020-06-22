package pattern.create.factory;

public class FruitFactory {

    public static Fruit newInstance(String name) throws Exception {
        if ("apple".equalsIgnoreCase(name)) {
            return new Apple();

        } else if ("grape".equalsIgnoreCase(name)) {
            return new Grape();

        } else if ("strawberry".equalsIgnoreCase(name)) {
            return new Strawberry();
        } else {
            throw new BadFruitException("Bad fruit request!");
        }
    }
}
