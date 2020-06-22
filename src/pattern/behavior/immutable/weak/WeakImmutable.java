package pattern.behavior.immutable.weak;

class Person {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setAge(int age) { this.age = age; }
    public int getAge() { return age; }

    public String toString() {
        return "name:"+name+", age:"+age;
    }
}

// Complex是"弱不变类"。
// 它虽然包含了可变的外部对象person，但是Complex内部的person是不会改变的。因为它保存的是person的拷贝。
class Complex {
    private Person person;
    private int id;

    public Complex (Person person, int id) {
        // 新建一个person的拷贝。
        this.person = new Person(person.getName(), person.getAge());
        this.id = id;
    }

    public int getId() { return id; }
    public Person getPerson() { return person; }

    public String toString() {
        return "id:"+id+", person("+person+")";
    }
}

/**
 * (01) 所考虑的对象没有任何方法会修改对象的状态；这样一来，当对象的构造函数将对象的状态初始化之后，对象的状态便不再改变。
 * (02) 所有属性都应当是私有的。不要声明任何的公开的属性，以防客户端对象直接修改任何的内部状态。
 * (03) 这个对象所引用到的其他对象如何是可变对象的话，必须设法限制外界对这些可变对象的访问，以防止外界修改这些对象。如何可能，应当尽量在不变对象内部初始化这些被引用的对象，
 * 而不要在客户端初始化，然后再传入到不变对象内部来。如果某个可变对象必须在客户端初始化，然后再传入到不变对象里的话，就应当考虑在不变对象初始化的时候，将这个可变对象复制一份，而不要使用原来的拷贝。
 */
public class WeakImmutable {
    public static void main(String[] args) {
        // 新建Complex对象。
        // id是1, person的名字是"张三"，年龄是18
        Person person = new Person("张三", 18);
        Complex com = new Complex(person, 1);
        System.out.println(com);

        // 修改person的名字为"李四"，年龄为20
        person.setName("李四");
        person.setAge(20);
        System.out.println(com);
    }
}
