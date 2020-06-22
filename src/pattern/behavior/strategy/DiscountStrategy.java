package pattern.behavior.strategy;

abstract class DiscountStrategy {

    // 价格
    protected int price = 0;
    // 策略
    protected int copies = 0;

    public abstract int calculateDiscount();

    public DiscountStrategy(int price, int copies) {
        this.price = price;
        this.copies = copies;
    }
}
