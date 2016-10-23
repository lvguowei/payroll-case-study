package main.java.payroll;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction {

    private double itsSalary;

    public ChangeSalariedTransaction(int itsEmpId, double itsSalary) {
        super(itsEmpId);
        this.itsSalary = itsSalary;
    }

    @Override
    public PaymentClassification getClassification() {
        return new SalariedClassification(itsSalary);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }
}
