package fr.lacombe.magic6tem;

import java.time.LocalTime;

public class Conference {

    private static final LocalTime LIMIT_HOUR = LocalTime.of(21, 0);
    private LocalTime arrivalTime;

    public Conference(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getColdMeals() {

        if(arrivalTime.isAfter(LIMIT_HOUR)) {
            return 1;
        }
        return 0;
    }
}
