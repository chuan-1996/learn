package pattern.create.prototype.prototype2;

import java.util.Vector;

public class PrototypeTest {
    /**登记形式*/
    public interface Prototype extends Cloneable {
        Prototype clone();
    }
    public static class ConcretePrototype implements Prototype {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
        @Override
        public synchronized Prototype clone() {
            Prototype temp = null;
            try {
                temp = (Prototype)super.clone();

            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return temp;
        }
    }
    public static class PrototypeManager {
        private Vector objects = new Vector();

        public void add(Prototype object) {
            objects.add(object);
        }

        public Prototype get(int i) {
            return (Prototype) objects.get(i);
        }

        public int getSize() {
            return objects.size();
        }
    }
    public static class Client {
        public PrototypeManager mgr;
        private Prototype prototype;
        public void registerPrototype(String id) {
            prototype = new ConcretePrototype();
            Prototype copytype = prototype.clone();
            mgr.add(copytype);
        }
    }

}
