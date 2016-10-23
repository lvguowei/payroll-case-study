package main.java.payroll;

public class TimeCard {
    private long itsDate;
    private double itsHours;

    public TimeCard(long itsDate, double itsHours) {
        this.itsDate = itsDate;
        this.itsHours = itsHours;
    }

    public long getDate() {
        return itsDate;
    }

    public double getHours() {
        return itsHours;
    }
}
