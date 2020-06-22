package pattern.structure.flyweight.normal;

/**
 * 具体享元
 */
public class ConcreteFlyweight extends Flyweight {
    private Character intrinsicState = null;

    /** 内蕴状态作为参数传入*/
    ConcreteFlyweight(Character state) {
        this.intrinsicState = state;
    }

    /** 外蕴状态作为参量传入方法中，改变方法的行为。*/
    @Override
    public void operation(String state) {
        System.out.println("\tIntrisic State="
                + intrinsicState
                + ", Extriinsic State="+state);
    }
}
