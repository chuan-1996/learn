package pattern.behavior.iterator.intrinsic;

abstract public class Aggregate {
    // 工厂方法：返回迭代器对象
    public abstract Iterator createIterator();
}
