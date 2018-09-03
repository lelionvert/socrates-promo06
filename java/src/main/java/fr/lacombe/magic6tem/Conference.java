package fr.lacombe.magic6tem;

import java.time.LocalTime;
import java.util.ArrayList;

public class Conference {

    private static final LocalTime LIMIT_HOUR = LocalTime.of(21, 0);
    private ArrayList<Participant> participants;

    public Conference(ArrayList<Participant> participants) {
        this.participants = participants;
    }

    public int getColdMeals() {
        return ((int) this.participants.stream()
                .filter(participant1 -> participant1.isArrivalTimeAfter(LIMIT_HOUR)).count());
    }

}
