package fr.lacombe.magic6tem.restaurant;

import fr.lacombe.magic6tem.conference.Participant;

import java.util.List;
import java.util.Objects;

public class Meal {

    private long vegetarians = 0;
    private long vegans = 0;
    private long pescatarians = 0;
    private long omnivorous = 0;

    public Meal(int omnivorous) {
        this.omnivorous = omnivorous;
    }

    public Meal() {
    }

    private void addVegetariansCovers(long vegetarians) {
        this.vegetarians += vegetarians;
    }

    private void addVegansCovers(long vegans) {
        this.vegans += vegans;
    }

    private void addPescatariansCovers(long pescatarians) {
        this.pescatarians += pescatarians;
    }

    private void addOmnivorousCovers(long omnivorous) {
        this.omnivorous += omnivorous;
    }

    public void addCoversByDiet(Diet diet, long numberEater){
        switch (diet)
        {
            case OMNIVOROUS:
                addOmnivorousCovers(numberEater);
                break;
            case VEGETARIAN:
                addVegetariansCovers(numberEater);
                break;
            case PESCATARIAN:
                addPescatariansCovers(numberEater);
                break;
            case VEGAN:
                addVegansCovers(numberEater);
                break;
        }
    }

    public Meal(int omnivorous, int vegetarians) {
        this.omnivorous = omnivorous;
        this.vegetarians = vegetarians;
    }

    public static Meal from(List<Participant> participants) {
        Meal meal = new Meal();
        meal.vegetarians = getCountForDiet(participants, Diet.VEGETARIAN);
        meal.vegans = getCountForDiet(participants, Diet.VEGAN);
        meal.pescatarians = getCountForDiet(participants, Diet.PESCATARIAN);
        meal.omnivorous = getCountForDiet(participants, Diet.OMNIVOROUS);
        return meal;
    }

    private static long getCountForDiet(List<Participant> participants, Diet searchedDiet) {
        return participants.stream().map(Participant::getDiet)
                .filter(diet -> diet.equals(searchedDiet)).count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return vegetarians == meal.vegetarians &&
                vegans == meal.vegans &&
                pescatarians == meal.pescatarians &&
                omnivorous == meal.omnivorous;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vegetarians, vegans, pescatarians, omnivorous);
    }

    @Override
    public String toString() {
        return "Meal\n" +
            "omnivorous :\t" + omnivorous +
            "\nvegetarians :\t" + vegetarians +
            "\nvegans :\t\t" + vegans +
            "\npescatarians :\t" + pescatarians;
    }
}
