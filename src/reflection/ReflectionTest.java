package reflection;

public class ReflectionTest {

    public static void main(String[] args) {

        try {
            // 根据“类名”获取 对应的Class对象
            Class<?> cls = Class.forName("reflection.Person");

            // 新建对象。newInstance()会调用类不带参数的构造函数
            Object obj = cls.newInstance();

            System.out.println("cls="+cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Person {
    public Person() {
        System.out.println("create Person");
    }
}
