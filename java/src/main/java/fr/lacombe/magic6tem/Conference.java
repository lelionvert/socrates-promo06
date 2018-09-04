package fr.lacombe.magic6tem;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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

    public Conference(List<Participant> participants, int meals) {
        this.participants = participants;
        this.meals = meals;
    }

    public int getColdMeals() {
        return ((int) this.participants.stream()
                .filter(participant1 -> participant1.isArrivalTimeAfter(LIMIT_HOUR)).count());
    }

    public List<String> getCoversByDiet(String diet) {
        if (participants.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> coversList = new ArrayList<>();
        for(int i=0; i<meals * participants.size(); i++) {
            coversList.add(diet);
        }

        return coversList;
    }
}
