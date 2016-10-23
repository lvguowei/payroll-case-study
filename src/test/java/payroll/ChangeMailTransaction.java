package test.java.payroll;

import main.java.payroll.ChangeMethodTransaction;
import main.java.payroll.MailMethod;
import main.java.payroll.PaymentMethod;

public class ChangeMailTransaction extends ChangeMethodTransaction {

    private String itsAddress;

    public ChangeMailTransaction(int itsEmpId, String itsAddress) {
        super(itsEmpId);
        this.itsAddress = itsAddress;
    }

    @Override
    public PaymentMethod getMethod() {
        return new MailMethod(itsAddress);
    }
}
