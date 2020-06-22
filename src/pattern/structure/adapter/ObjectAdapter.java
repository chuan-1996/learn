package pattern.structure.adapter;

public class ObjectAdapter {
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
        private Adaptee adaptee;

        public Adapter(Adaptee adaptee) {
            super();
            this.adaptee = adaptee;
        }
        // 调用"源"类中已有的方法
        @Override
        public void getPower110V() {
            adaptee.getPower110V();
        }
        @Override
        public void getPower220V() {
            System.out.println("get power: 220V");
        }
    }



        public static void main(String[] args) {
            Adaptee adaptee = new Adaptee();
            Target target = new Adapter(adaptee);
            target.getPower220V();
        }

}
