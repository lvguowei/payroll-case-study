package main.java.payroll;

public class ChangeDirectTransaction extends ChangeMethodTransaction {

    private String bank;
    private String account;

    public ChangeDirectTransaction(int itsEmpId, String bank, String account) {
        super(itsEmpId);
        this.bank = bank;
        this.account = account;
    }

    @Override
    public PaymentMethod getMethod() {
        return new DirectMethod(bank, account);
    }
}
