package pattern.behavior.template;

abstract public class Account {

    protected String accountNumber;

    public Account() {
        accountNumber = null;
    }

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // 模板方法，计算利息数额
    public final double calculateInterest(){
        double interestRate = doCalculateInterestRate();
        String accountType = doCalculateAccountType();
        double amount = calculateAmount(accountType, accountNumber);
        return amount * interestRate;
    }

    // 基本方法留给子类实现
    protected abstract String doCalculateAccountType();

    // 基本方法留给子类实现
    protected abstract double doCalculateInterestRate();

    // 基本方法，已经实现
    private double calculateAmount(String accountType, String accountNumber){
        // retrive amount from database
        return 7243.00D;
    }
}
