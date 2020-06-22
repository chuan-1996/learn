package pattern.behavior.template;

/**
 * Account是抽象模板类，calculateInterest()是模板方法
 */
public class TemplateTest {
    private static Account acct = null;
    public static void main(String[] args) {
        acct = new MoneyMarketAccount();
        System.out.println("Interest from Money Market account: " + acct.calculateInterest());
        acct = new CDAccount();
        System.out.println("Interest from CD account: " + acct.calculateInterest());
    }
}
