package main.java.payroll;

public class TimeCardTransaction implements Transaction {

    private int itsEmpid;
    private long itsDate;
    private double itsHours;

    public TimeCardTransaction(int empid, long date, double hours) {
        this.itsEmpid = empid;
        this.itsDate = date;
        this.itsHours = hours;
    }

    @Override
    public void execute() {
        Employee e = PayrollDatabase.instance.getEmployee(itsEmpid);
        if (e != null) {
            PaymentClassification pc = e.getClassification();
            if (pc instanceof HourlyClassification) {
                HourlyClassification hc = (HourlyClassification) pc;
                hc.addTimeCard(new TimeCard(itsDate, itsHours));
            } else {
                throw new RuntimeException("Tried to add timecard to non-hourly employee");
            }
        } else {
            throw new RuntimeException("No such employee");
        }
    }
}
