package fr.lacombe.magic6tem.restaurant;

import fr.lacombe.magic6tem.conference.Participant;

import java.util.List;
import java.util.Objects;

public class Meal {

    private int vegetarians = 0;
    private int vegans = 0;
    private int pescatarians = 0;
    private int omnivorous = 0;

    private Meal() {
    }

    public static Meal from(List<Participant> participants) {
        Meal meal = new Meal();
        meal.vegetarians = getCountForDiet(participants, Diet.VEGETARIAN);
        meal.vegans = getCountForDiet(participants, Diet.VEGAN);
        meal.pescatarians = getCountForDiet(participants, Diet.PESCATARIAN);
        meal.omnivorous = getCountForDiet(participants, Diet.OMNIVOROUS);
        return meal;
    }

    private static int getCountForDiet(List<Participant> participants, Diet searchedDiet) {
        return (int) participants.stream().map(Participant::getDiet)
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

    public static final class MealBuilder {
        private int vegetarians = 0;
        private int vegans = 0;
        private int pescatarians = 0;
        private int omnivorous = 0;

        private MealBuilder() {
        }

        public static MealBuilder aMeal() {
            return new MealBuilder();
        }

        public MealBuilder withVegetarians(int vegetarians) {
            this.vegetarians = vegetarians;
            return this;
        }

        public MealBuilder withVegans(int vegans) {
            this.vegans = vegans;
            return this;
        }

        public MealBuilder withPescatarians(int pescatarians) {
            this.pescatarians = pescatarians;
            return this;
        }

        public MealBuilder withOmnivorous(int omnivorous) {
            this.omnivorous = omnivorous;
            return this;
        }

        public Meal build() {
            Meal meal = new Meal();
            meal.vegans = this.vegans;
            meal.omnivorous = this.omnivorous;
            meal.vegetarians = this.vegetarians;
            meal.pescatarians = this.pescatarians;
            return meal;
        }
    }
}
