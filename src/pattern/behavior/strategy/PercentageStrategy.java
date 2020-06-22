package pattern.behavior.strategy;

public class PercentageStrategy extends DiscountStrategy {

    // 折扣比例
    private float percent = 0.8f;

    public PercentageStrategy(int price, int copies) {
        super(price, copies);
    }

    public void setPercent() {
        this.percent = percent;
    }

    public float getPercent() {
        return percent;
    }

    public int calculateDiscount() {
        return (int)(copies*price*percent);
    }
}
