package fr.lacombe.magic6tem;

import java.time.LocalDateTime;

public class CheckIn {

    private final LocalDateTime arrivalDateTime;

    public CheckIn(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    boolean isAfter(LocalDateTime dateTime) {
        return arrivalDateTime.isAfter(dateTime);
    }
}
