package pattern.create.factory.abstract2;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        Factory appleFac = new AppleFactory();
        Phone applePhone = appleFac.createPhone();
        Computer appleComputer = appleFac.createComputer();
        applePhone.activate();
        System.out.println("appleOS is: "+appleComputer.getOSName());

        Factory sumFac = new SumFactory();
        Phone sumPhone = sumFac.createPhone();
        Computer sumComputer = sumFac.createComputer();
        sumPhone.activate();
        System.out.println("sumOS is: "+sumComputer.getOSName());
    }
}
