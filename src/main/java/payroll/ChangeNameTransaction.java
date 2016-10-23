package main.java.payroll;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {

    private String itsName;

    public ChangeNameTransaction(int itsEmpId, String itsName) {
        super(itsEmpId);
        this.itsName = itsName;
    }

    @Override
    public void change(Employee employee) {
        employee.setName(itsName);
    }
}
