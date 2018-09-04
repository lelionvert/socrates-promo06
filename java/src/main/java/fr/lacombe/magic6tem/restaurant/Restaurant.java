package fr.lacombe.magic6tem.restaurant;

import fr.lacombe.magic6tem.conference.Participant;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Restaurant {

    private static final LocalTime LIMIT_HOUR = LocalTime.of(21, 0);
    private int meals = 1;
    private List<Participant> participants;

    public Restaurant(List<Participant> participants) {
        this.participants = participants;
    }

    public Restaurant(List<Participant> participants, int meals) {
        this.participants = participants;
        this.meals = meals;
    }

    public int getColdMeals() {
        return ((int) this.participants.stream()
                .filter(this::isArrivingAfterDinner).count());
    }

    private boolean isArrivingAfterDinner(Participant participant) {
        return participant.isArrivalTimeAfter(LIMIT_HOUR);
    }

    private boolean isArrivingBeforeDinner(Participant participant) {
        return participant.isArrivalTimeBefore(LIMIT_HOUR);
    }

    public List<Diet> getCoversByDiet() {
        if (participants.isEmpty()) {
            return new ArrayList<>();
        }

        List<Diet> coversList = new ArrayList<>();
        for (int i = 0; i < meals; i++) {
            for (Participant participant : participants) {
                if (i == 0 && isArrivingAfterDinner(participant)) {
                    continue;
                }
                coversList.add(participant.getDiet());
            }
        }
        return coversList;
    }

    public List<Covers> getCoversByDiet2() {
        if (participants.isEmpty()) {
            return new ArrayList<>();
        }

        List<Covers> coversList = new ArrayList<>();
        for (int meal = 0; meal < meals; meal++) {
            if (meal == 0) {
                coversList.add(Covers.from(this.participants.stream()
                        .filter(this::isArrivingBeforeDinner).collect(Collectors.toList())));
                continue;
            }
            coversList.add(Covers.from(this.participants));
        }
        return coversList;
    }
}
