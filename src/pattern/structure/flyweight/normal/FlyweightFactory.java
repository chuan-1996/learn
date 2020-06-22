package pattern.structure.flyweight.normal;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class FlyweightFactory {
    private HashMap flies = new HashMap();

    public Flyweight factory(Character state) {
        if (flies.containsKey(state)) {
            System.out.println(state+" exists.");
            return (Flyweight)flies.get(state);
        } else {
            System.out.println(state+" not exists!");
            Flyweight fly = new ConcreteFlyweight(state);
            flies.put(state, fly);
            return fly;
        }
    }
}
