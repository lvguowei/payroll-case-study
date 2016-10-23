package main.java.payroll;

public class ChangeAddressTransaction extends ChangeEmployeeTransaction {

    String itsAddress;

    public ChangeAddressTransaction(int itsEmpId, String itsAddress) {
        super(itsEmpId);
        this.itsAddress = itsAddress;
    }

    @Override
    public void change(Employee employee) {
        employee.setAddress(itsAddress);
    }
}
