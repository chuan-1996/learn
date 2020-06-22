package pattern.create;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * normal
 * 饿汉式
 * lazy loading: false
 */
class Singleton implements Serializable{
    private static Singleton instance = new Singleton();
    private Singleton (){
    }
    public static Singleton getInstance() {
        return instance;
    }
}

/**
 * DCL  double-checked locking
 * 懒汉式
 * lazy loading: true
 */
class Singleton2 implements Serializable{
    private volatile static Singleton2 singleton;
    private Singleton2(){
    }
    public static Singleton2 getInstance() {
        if (singleton == null) {
            synchronized (Singleton2.class) {
                if (singleton == null) {
                    singleton = new Singleton2();
                }
            }
        }
        return singleton;
    }
}

/**
 * ENUM
 * lazy loading: false
 * 推荐使用
 * 非常简洁，防反射 序列化攻击
 */
enum  Singleton3 implements Serializable,Runnable{
    /**
     */
    INSTANCE;
    private int a = 3;
    Singleton3(){
    }

    @Override
    public void run(){
        System.out.println("run!!!!!!!!!");
    }

    private void run2(){
        System.out.println("run");
    }

    void setA(int a){this.a=a;run2();}
    int getA(){return this.a;}
    void hello(){
        System.out.println("SOMETHING");
    }
}
/**
 * @author qq491
 * @see Singleton3 is best    java 1.5+
 */
public class SingletonTest {
    public static void main(String[] args) throws Exception {
        // reflectionAttack(3);
        // serializationAttack(3);
    }


    public static void reflectionAttack(int i) throws Exception {
        if(i == 1){
            Constructor constructor = Singleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            Singleton s1 = (Singleton)constructor.newInstance();
            Singleton s2 = (Singleton)constructor.newInstance();
            System.out.println(s1.hashCode());
            System.out.println(s2.hashCode());
            System.out.println(s1 == s2);
        }
        else if(i == 2){
            Constructor constructor = Singleton2.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            Singleton2 s1 = (Singleton2)constructor.newInstance();
            Singleton2 s2 = (Singleton2)constructor.newInstance();
            System.out.println(s1.hashCode());
            System.out.println(s2.hashCode());
            System.out.println(s1 == s2);
        }
        else if(i == 3){
            Constructor constructor = Singleton3.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            Singleton3 s1 = (Singleton3)constructor.newInstance();
            Singleton3 s2 = (Singleton3)constructor.newInstance();
            System.out.println(s1.hashCode());
            System.out.println(s2.hashCode());
            System.out.println(s1 == s2);
        }
    }

    public static void serializationAttack(int i) throws Exception {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("serFile"));
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("serFile")));
        if(i == 1){
            Singleton s1 = Singleton.getInstance();
            outputStream.writeObject(s1);
            Singleton s2 = (Singleton)inputStream.readObject();
            System.out.println(s1.hashCode());
            System.out.println(s2.hashCode());
            System.out.println(s1 == s2);
        }
        else if(i == 2){
            Singleton2 s1 = Singleton2.getInstance();
            outputStream.writeObject(s1);
            Singleton2 s2 = (Singleton2)inputStream.readObject();
            System.out.println(s1.hashCode());
            System.out.println(s2.hashCode());
            System.out.println(s1 == s2);
        }
        else if(i == 3){
            Singleton3 s1 = Singleton3.INSTANCE;
            outputStream.writeObject(s1);
            Singleton3 s2 = (Singleton3)inputStream.readObject();
            System.out.println(s1.hashCode());
            System.out.println(s2.hashCode());
            System.out.println(s1 == s2);
        }

    }
}
