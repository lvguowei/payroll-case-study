package main.java.payroll;

public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {

    public ChangeMethodTransaction(int itsEmpId) {
        super(itsEmpId);
    }

    public abstract PaymentMethod getMethod();

    @Override
    public void change(Employee employee) {
        employee.setMethod(getMethod());
    }
}
