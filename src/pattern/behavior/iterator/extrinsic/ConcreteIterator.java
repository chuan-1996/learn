package pattern.behavior.iterator.extrinsic;

/**
 * 如果迭代器是在聚集结构之外实现的，这样的迭代器被称为外禀迭代器(Extrinsic Iterator)。
 */
public class ConcreteIterator implements Iterator {
    private ConcreteAggregate agg;
    // 索引位置
    private int index = 0;
    // 集合大小
    private int size = 0;

    public ConcreteIterator(ConcreteAggregate agg){
        this.agg = agg;
        this.size = agg.size();
        index = 0;
    }

    // 迭代方法：移动到第一个元素
    @Override
    public void first() {
        index = 0;
    }

    // 迭代方法：是否为最后一个元素
    @Override
    public boolean isDone() {
        return (index >= size);
    }

    // 迭代方法：移动到下一个元素
    @Override
    public void next() {
        if(index < size) {
            index ++;
        }
    }

    // 迭代方法：返还当前元素
    @Override
    public Object currentItem() {
        return agg.getElement(index);
    }
}
