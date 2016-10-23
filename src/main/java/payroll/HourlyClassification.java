package main.java.payroll;

import java.util.ArrayList;
import java.util.List;

public class HourlyClassification extends PaymentClassification {

    private List<TimeCard> timeCards;

    private double hourlyRate;

    public HourlyClassification(double hourlyRate) {
        this.hourlyRate = hourlyRate;
        timeCards = new ArrayList<>();
    }

    public double getRate() {
        return hourlyRate;
    }

    public void addTimeCard(TimeCard timeCard) {
        timeCards.add(timeCard);
    }

    public TimeCard getTimeCard(long date) {
        for (TimeCard tc : timeCards) {
            if (tc.getDate() == date) {
                return tc;
            }
        }
        return null;
    }
}
