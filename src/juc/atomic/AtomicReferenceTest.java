package juc.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

    public static void main(String[] args){
        Person p1 = new Person(101);
        Person p2 = new Person(102);

        AtomicReference ar = new AtomicReference(p1);
        p1.id=103;
        Person p4 = (Person)ar.get();
        System.out.println("p4 is "+p4);
        ar.compareAndSet(p1, p2);
        Person p3 = (Person)ar.get();
        System.out.println("p3 is "+p3);
    }
}

class Person {
    volatile long id;
    public Person(long id) {
        this.id = id;
    }
    public String toString() {
        return "id:"+id;
    }
}
