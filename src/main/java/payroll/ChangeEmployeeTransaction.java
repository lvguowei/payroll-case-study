package main.java.payroll;

public abstract class ChangeEmployeeTransaction implements Transaction {

    int itsEmpId;

    public ChangeEmployeeTransaction(int itsEmpId) {
        this.itsEmpId = itsEmpId;
    }

    public abstract void change(Employee employee);

    @Override
    public void execute() {
        Employee e = PayrollDatabase.instance.getEmployee(itsEmpId);
        if (e != null) {
            change(e);
        }
    }
}
