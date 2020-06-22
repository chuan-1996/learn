package Collection;

import java.util.Iterator;
import java.util.Vector;

public class VectorTest {
    static class table{
        double a;
        double b;
        table(double a,double b){
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "table{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }
    public static void main(String[] args){
        Vector<table> a = new Vector<>();
        a.add(new table(30.0D,40.0D));
        a.add(new table(3.0D,410.0D));
        a.add(new table(40.0D,0.04D));
        a.add(new table(0.1D,4.0D));

        Iterator b = a.iterator();
        while (b.hasNext()){
            System.out.println(b.next());
        }
        System.out.println( a.get(0));
    }

}
