package fr.lacombe.magic6tem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckIn {

    private final LocalDateTime arrivalDateTime;

    private CheckIn(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    boolean isAfter(LocalDateTime dateTime) {
        if (dateTime == null){
            throw new IllegalArgumentException("The date should be not null");
        }
        return arrivalDateTime.isAfter(dateTime);
    }


    public static CheckIn of(LocalDateTime dateTime){
        if(dateTime.toLocalDate().isAfter(ConferenceCalendar.ARRIVAL_DAY.date)){
            throw new IllegalArgumentException("CheckIn Date cannot be after Arrival Day");
        }
        return new CheckIn(dateTime);
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "\narrivalDateTime=" + arrivalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                '}';
    }
}
