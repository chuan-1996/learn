package pattern.create.factory.abstract2;

public class AppleFactory implements Factory{
    // 生产苹果手机
    @Override
    public Phone createPhone() {
        return new ApplePhone();
    }
    // 生产苹果电脑
    @Override
    public Computer createComputer() {
        return new AppleComputer();
    }
}
