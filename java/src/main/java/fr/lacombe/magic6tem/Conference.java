package fr.lacombe.magic6tem;

import java.time.LocalTime;
import java.util.List;

public class Conference {

    private static final LocalTime LIMIT_HOUR = LocalTime.of(21, 0);
    private List<LocalTime> arrivalTimes;

    public Conference(List<LocalTime> arrivalTimes) {
        this.arrivalTimes = arrivalTimes;
    }

    public int getColdMeals() {
        return ((int) this.arrivalTimes.stream()
                .filter(localTime -> localTime.isAfter(LIMIT_HOUR)).count());

    }
}
