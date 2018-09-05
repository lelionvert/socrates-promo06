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

    public List<Meal> getMealsByDiet() {
        if (participants.isEmpty()) {
            return new ArrayList<>();
        }

        List<Meal> meals = new ArrayList<>();
        for (int meal = 0; meal < this.meals; meal++) {
            if (meal == 0) {
                meals.add(Meal.from(this.participants.stream()
                        .filter(this::isArrivingBeforeDinner).collect(Collectors.toList())));
                continue;
            }
            meals.add(Meal.from(this.participants));
        }
        return meals;
    }
}
