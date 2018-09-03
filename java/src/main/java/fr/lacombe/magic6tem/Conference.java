package fr.lacombe.magic6tem;

import java.time.LocalTime;

public class Conference {

    private static final LocalTime LIMIT_HOUR = LocalTime.of(21, 0);
    private LocalTime arrivalTime;
    private final LocalTime arrivalTime2;

    public Conference(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
        this.arrivalTime2 = null;
    }

    public Conference(LocalTime arrivalTime, LocalTime arrivalTime2) {
        this.arrivalTime = arrivalTime;
        this.arrivalTime2 = arrivalTime2;
    }

    public int getColdMeals() {

        if(arrivalTime.isAfter(LIMIT_HOUR)) {
            return 1;
        }
        return 0;
    }
}
