package main.java.payroll;

public class SalesReceiptTransaction implements Transaction {

    private long date;
    private double amount;
    private int empid;

    public SalesReceiptTransaction(long date, double amount, int empid) {
        this.date = date;
        this.amount = amount;
        this.empid = empid;
    }

    @Override
    public void execute() {
        Employee e = PayrollDatabase.instance.getEmployee(empid);
        if (e != null) {
            PaymentClassification pc = e.getClassification();
            if (pc instanceof CommissionedClassification) {
                CommissionedClassification cc = (CommissionedClassification) pc;
                cc.addSalesReceipt(new SalesReceipt(date, amount));
            } else {
                throw new RuntimeException("Tried to add sales receipt to non-commissioned employee");
            }
        } else {
            throw new RuntimeException("Employee not found");
        }
    }
}
