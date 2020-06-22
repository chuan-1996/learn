package pattern.behavior.immutable.strong;

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

/**
 * 第一，将Complex中的属性和方法都修改为final类型。
 * 第二，将Complex类本身修改为final类型。
 */
final class Complex {
    private final Person person;
    private final int id;

    public Complex (Person person, int id) {
        // 新建一个person的拷贝。
        this.person = new Person(person.getName(), person.getAge());
        this.id = id;
    }

    public final int getId() { return id; }
    public final Person getPerson() { return person; }

    public String toString() {
        return "id:"+id+", person("+person+")";
    }
}

public class StrongImmutable {
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
