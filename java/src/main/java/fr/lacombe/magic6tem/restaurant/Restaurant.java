package fr.lacombe.magic6tem.restaurant;

import fr.lacombe.magic6tem.conference.Participant;
import fr.lacombe.magic6tem.conference.OrganisationMeal;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Restaurant {

    private static final LocalTime LIMIT_HOUR = LocalTime.of(21, 0);
    private OrganisationMeal organisationMeal;

    private List<Participant> participants;

    public Restaurant(List<Participant> participants, OrganisationMeal organisationMeal) {
        this.participants = participants;
        this.organisationMeal = organisationMeal;
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

    public MealsListOfConference getMealsByDiet() {
        if (participants.isEmpty()) {
            return new MealsListOfConference();
        }

        MealsListOfConference meals = new MealsListOfConference();
        for (int numberMeal = 0; numberMeal < organisationMeal.getMealsOfTheConference().size(); numberMeal++) {
            Stream<Participant> stream = this.participants.stream();
            Stream<Participant> participantStream;

            if (isFirstMeal(numberMeal)) {
                participantStream = stream
                    .filter(this::isArrivingBeforeDinner);
            }
            else{
                participantStream = stream;
            }

            meals.add(Covers.from(participantStream
                .map(Participant::getDiet)
                .collect(Collectors.toList())));
        }
        return meals;
    }

    private boolean isFirstMeal(int numberMeal) {
        return numberMeal == 0;
    }

    public Covers getMeal(String mealTime) {

        int indexOfMealTime = organisationMeal.getMealsOfTheConference().indexOf(mealTime);
        if(organisationMeal.getMealsOfTheConference().contains(mealTime)){
            return getMealsByDiet().getMealByIndex(indexOfMealTime);
        } else {
            throw new IllegalArgumentException(mealTime + " is not a good meal name");
        }
    }
}
