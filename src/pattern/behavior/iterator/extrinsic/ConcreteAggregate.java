package pattern.behavior.iterator.extrinsic;

public class ConcreteAggregate extends Aggregate {

    private Object[] objs = {"Monk Tang",
            "Monkey", "Pigsy",
            "Sandy", "Horse"};

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    // 取值方法：向外界提供聚集元素
    public Object getElement(int index){

        if(index < objs.length){
            return objs[index];
        }else{
            return null;
        }
    }

    // 取值方法：向外界提供聚集的大小
    public int size(){
        return objs.length;
    }
}
