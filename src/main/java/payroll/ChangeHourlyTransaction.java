package main.java.payroll;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {

    private double itsHourlyRate;

    public ChangeHourlyTransaction(int itsEmpId, double rate) {
        super(itsEmpId);
        itsHourlyRate = rate;
    }

    @Override
    public PaymentClassification getClassification() {
        return new HourlyClassification(itsHourlyRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}
