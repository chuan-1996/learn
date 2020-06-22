package pattern.behavior.iterator.extrinsic;

public class IteratorTest {
    public static Iterator it;
    public static Aggregate agg = new ConcreteAggregate();

    public static void main(String[] args) {
        it = agg.createIterator();
        while(!it.isDone()) {
            System.out.println(it.currentItem());
            it.next();
        }
    }
}
