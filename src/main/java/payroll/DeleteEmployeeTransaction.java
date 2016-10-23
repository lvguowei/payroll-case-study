package main.java.payroll;

public class DeleteEmployeeTransaction implements Transaction {

    private int employId;

    public DeleteEmployeeTransaction(int employId) {
        this.employId = employId;
    }

    @Override
    public void execute() {
        PayrollDatabase.instance.deleteEmployee(employId);
    }
}
