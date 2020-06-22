package pattern.behavior.iterator.intrinsic;

public class ConcreteAggregate extends Aggregate {

    private Object[] objs = {"Monk Tang",
            "Monkey", "Pigsy",
            "Sandy", "Horse"};

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator();
    }

    private class ConcreteIterator
            implements Iterator {
        // 索引位置
        private int index = 0;

        // 迭代方法：移动到第一个元素
        @Override
        public void first() {
            index = 0;
        }

        // 迭代方法：是否为最后一个元素
        @Override
        public boolean isDone() {
            return (index == objs.length);
        }

        // 迭代方法：移动到下一个元素
        @Override
        public void next() {
            if(index < objs.length) {
                index ++;
            }
        }

        // 迭代方法：返还当前元素
        @Override
        public Object currentItem() {
            return objs[index];
        }
    }
}
