package main.java.payroll;

public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {

    private double itsSalary;
    private double itsCommissionRate;

    public ChangeCommissionedTransaction(int itsEmpId, double itsSalary, double itsCommissionRate) {
        super(itsEmpId);
        this.itsSalary = itsSalary;
        this.itsCommissionRate = itsCommissionRate;
    }

    @Override
    public PaymentClassification getClassification() {
        return new CommissionedClassification(itsSalary, itsCommissionRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }
}
