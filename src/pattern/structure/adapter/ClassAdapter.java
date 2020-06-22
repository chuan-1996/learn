package pattern.structure.adapter;

/**
 * @author qq491
 * 类适配器把"被适配的类的API" 转换成 "目标类的API"
 * @see 目标(Target) 提供给客户端的接口。由于"源"中的接口不能满足需求，因此就扩展出来了目标，它包括了客户所需的接口。
 * 在适配器模式中，目标角色对应的类一般是抽象类或接口，而不是实例类
 * @see 源(Adaptee) 现有的接口。
 * @see 适配器(Adapter)  把源接口转换成目标接口。
 */
public class ClassAdapter {
    interface Target {
        /** Adaptee能提供的电压110V*/
        void getPower110V() ;

        /** 客户需要的电压220V*/
        void getPower220V() ;
    }

    static class Adaptee {
        public void getPower110V() {
            System.out.println("get power: 110V");
        }

    }

    static class Adapter extends Adaptee implements Target{
        @Override
        public void getPower220V() {
            System.out.println("get power: 220V");
        }
    }


    public static void main(String[] args) {
        Target target = new Adapter();
        target.getPower220V();
    }

}
