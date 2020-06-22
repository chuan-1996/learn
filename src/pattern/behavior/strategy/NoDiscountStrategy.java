package pattern.behavior.strategy;

public class NoDiscountStrategy extends DiscountStrategy {

    public NoDiscountStrategy(int price, int copies) {
        super(price, copies);
        }

    @Override
    public int calculateDiscount() {
            return copies*price;
            }
}
