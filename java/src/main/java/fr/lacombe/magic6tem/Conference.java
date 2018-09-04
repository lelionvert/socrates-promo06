package fr.lacombe.magic6tem;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
                .filter(this::isGoingToBeLateForFirstDinner).count());
    }

    private boolean isGoingToBeLateForFirstDinner(Participant participant) {
        return participant.isArrivalTimeAfter(LIMIT_HOUR);
    }

    public List<Diet> getCoversByDiet() {
        if (participants.isEmpty()) {
            return new ArrayList<>();
        }

        List<Diet> coversList = new ArrayList<>();
        for (Participant participant : participants) {
            for (int i = 0; i < meals; i++) {
                coversList.add(participant.getDiet());
            }
        }

        return coversList;
    }
}
