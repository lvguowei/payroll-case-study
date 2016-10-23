package main.java.payroll;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {

    public ChangeAffiliationTransaction(int itsEmpId) {
        super(itsEmpId);
    }

    public abstract Affiliation getAffiliation();
    public abstract void recordMembership(Employee employee);

    @Override
    public void change(Employee employee) {
        recordMembership(employee);
        employee.setAffiliation(getAffiliation());
    }
}
