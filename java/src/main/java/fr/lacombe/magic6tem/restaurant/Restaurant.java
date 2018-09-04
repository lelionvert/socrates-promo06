package fr.lacombe.magic6tem.restaurant;

import fr.lacombe.magic6tem.Participant;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private static final LocalTime LIMIT_HOUR = LocalTime.of(21, 0);
    private int meals = 1;
    private List<Participant> participants;

    public Restaurant(List<Participant> participants) {
        this.participants = participants;
    }

    public Restaurant() {
        participants = new ArrayList<>();
    }

    public Restaurant(List<Participant> participants, int meals) {
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
