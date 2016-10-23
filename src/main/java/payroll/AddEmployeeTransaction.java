package main.java.payroll;

public abstract class AddEmployeeTransaction implements Transaction {

    public int itsEmpid;
    public String itsAddress;
    public String itsName;

    public AddEmployeeTransaction(int empid, String name, String address) {
        this.itsEmpid = empid;
        this.itsAddress = address;
        this.itsName = name;
    }

    abstract PaymentClassification getClassification();

    abstract PaymentSchedule getSchedule();

    @Override
    public void execute() {
        Employee e = new Employee(itsEmpid, itsName, itsAddress);
        e.setClassification(getClassification());
        e.setSchedule(getSchedule());
        e.setMethod(new HoldMethod(itsAddress));
        PayrollDatabase.instance.addEmployee(itsEmpid, e);
    }
}
