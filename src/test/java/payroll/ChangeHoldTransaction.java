package test.java.payroll;

import main.java.payroll.ChangeMethodTransaction;
import main.java.payroll.HoldMethod;
import main.java.payroll.PaymentMethod;

public class ChangeHoldTransaction extends ChangeMethodTransaction {

    private String itsAddress;

    public ChangeHoldTransaction(int itsEmpId, String itsAddress) {
        super(itsEmpId);
        this.itsAddress = itsAddress;
    }

    @Override
    public PaymentMethod getMethod() {
        return new HoldMethod(itsAddress);
    }
}
