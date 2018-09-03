package fr.lacombe.magic6tem;

import java.time.LocalTime;

public class Participant {

    private LocalTime arrivalTime;

    public Participant(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    boolean isArrivalTimeAfter(LocalTime limitHour) {
        return arrivalTime.isAfter(limitHour);
    }
}
