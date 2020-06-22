package pattern.behavior.immutable.mutable;

class Person {
    private String name;
    private int age;
    Person(String name, int age) {
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

// Complex不是"弱不变类"。
// 因为它包含了可变类对象person，并且person是在外部初始化的。

class Complex {
    private Person person;
    private int id;

    Complex (Person person, int id) {
        this.person = person;
        this.id = id;
    }

    public int getId() { return id; }
    public Person getPerson() { return person; }

    public String toString() {
        return "id:"+id+", person("+person+")";
    }
}

public class Mutable{
    public static void main(String[] args) {
        // 新建Complex对象，在"外部(即，非Complex内部)"初始化person。
        Person person = new Person("张三", 18);
        Complex com = new Complex(person, 1);
        System.out.println(com);

        // 修改person的名字为"李四"，年龄为20
        person.setName("李四");
        person.setAge(20);
        System.out.println(com);
    }
}
