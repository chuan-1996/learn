package pattern.behavior.strategy;

public class FlatRateStrategy extends DiscountStrategy {

    private int discard = 5;

    public FlatRateStrategy(int price, int copies) {
        super(price, copies);
    }

    public void setDiscard() {
        this.discard = discard;
    }

    public int getDiscard() {
        return discard;
    }

    public int calculateDiscount() {
        return copies*(price - discard);
    }
}
