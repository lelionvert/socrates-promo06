package fr.lacombe.magic6tem;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Conference {

    private static final LocalTime LIMIT_HOUR = LocalTime.of(21, 0);
    private List<LocalTime> arrivalTimes;
    private Participant participant;

    public Conference(List<LocalTime> arrivalTimes) {
        this.arrivalTimes = arrivalTimes;
    }

    public Conference(Participant participant) {
        this.participant = participant;
    }


    public int getColdMeals() {
        return ((int) this.arrivalTimes.stream()
                .filter(localTime -> localTime.isAfter(LIMIT_HOUR)).count());
    }

    public int getColdMeals2() {
        return 0;
    }
}
