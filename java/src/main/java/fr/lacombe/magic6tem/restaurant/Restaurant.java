package fr.lacombe.magic6tem.restaurant;

import fr.lacombe.magic6tem.conference.Participant;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Meals getMealsByDiet() {
        if (participants.isEmpty()) {
            return new Meals(new ArrayList<>());
        }

        Meals meals = new Meals(new ArrayList<>());
        for (int numberMeal = 0; numberMeal < this.meals; numberMeal++) {
            Stream<Participant> stream = this.participants.stream();
            Stream<Participant> participantStream;

            if (isFirstMeal(numberMeal)) {
                participantStream = stream
                    .filter(this::isArrivingBeforeDinner);
            }
            else{
                participantStream = stream;
            }

            meals.add(Meal.from(participantStream
                .map(Participant::getDiet)
                .collect(Collectors.toList())));
        }
        return meals;
    }

    private boolean isFirstMeal(int numberMeal) {
        return numberMeal == 0;
    }

    public Meal getMeal(String mealTime) {
        return Meal.from(participants.stream()
            .map(Participant::getDiet).collect(Collectors.toList()));
    }
}
