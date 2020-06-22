package pattern.create.factory.abstract2;

public class SumFactory implements Factory{
    // 生产三星手机
    @Override
    public Phone createPhone() {
        return new SumPhone();
    }
    // 生产三星电脑
    @Override
    public Computer createComputer() {
        return new SumComputer();
    }
}
