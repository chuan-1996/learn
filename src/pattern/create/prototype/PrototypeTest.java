package pattern.create.prototype;

/**
 * @author qq491
 * 与通过对一个类进行实例化来构造新对象不同的是，原型模式是通过拷贝一个现有对象生成新对象的。
 * 拷贝一个对象消耗的资源低于实例化构造法
 * 浅拷贝实现 Cloneable，重写，深拷贝是通过实现 Serializable 读取二进制流。
 * 代码均为模型代码,不可运行
 */
public class PrototypeTest {
    /**简单形式*/
    public interface Prototype extends Cloneable {
        Prototype clone();
    }
    public static class ConcretePrototype implements Prototype {

        @Override
        public Prototype clone() {
            Prototype clone = null;
            try {
                clone = (Prototype)super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return clone;
        }
    }
    public static class Client {
        private Prototype prototype;
        public void operation(Prototype example) {
            Prototype p = (Prototype) example.clone();
        }
    }
}
