package fr.lacombe.magic6tem;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Conference {

    private static final LocalTime LIMIT_HOUR = LocalTime.of(21, 0);
    private int meals = 1;
    private List<Participant> participants;

    public Conference(List<Participant> participants) {
        this.participants = participants;
    }

    public Conference() {
        participants = new ArrayList<>();
    }

    public Conference(ArrayList<Participant> participants, int meals) {
        this.participants = participants;
        this.meals = meals;
    }

    public int getColdMeals() {
        return ((int) this.participants.stream()
                .filter(participant1 -> participant1.isArrivalTimeAfter(LIMIT_HOUR)).count());
    }

    public int getCoversByDiet() {
        if (participants.isEmpty()) {
            return 0;
        }
        return meals * participants.size();
    }
}
