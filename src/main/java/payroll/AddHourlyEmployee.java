package main.java.payroll;

public class AddHourlyEmployee extends AddEmployeeTransaction {

    public double hourlyRate;

    public AddHourlyEmployee(int empid, String name, String address, double hourlyRate) {
        super(empid, name, address);
        this.hourlyRate = hourlyRate;
    }

    @Override
    PaymentClassification getClassification() {
        return new HourlyClassification(hourlyRate);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}
