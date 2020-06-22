package pattern.behavior.strategy;

public class StrategyTest {
    public static void main(String[] args) {
        // 第一种策略
        DiscountStrategy strategy = new NoDiscountStrategy(60, 5);
        System.out.println("total(60,5)="+strategy.calculateDiscount());

        strategy = new FlatRateStrategy(80, 5);
        System.out.println("total(80,5)="+strategy.calculateDiscount());

        strategy = new PercentageStrategy(100, 3);
        System.out.println("total(100,3)="+strategy.calculateDiscount());

    }
}
