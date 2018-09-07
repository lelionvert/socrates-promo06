package fr.lacombe.magic6tem.restaurant;

import fr.lacombe.magic6tem.conference.Participant;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GetColdMealShould {

    private static final Participant LATE_PARTICIPANT = new Participant(LocalTime.of(22, 0));
    private static final Participant EARLY_PARTICIPANT = new Participant(LocalTime.of(20, 0));
    private ArrayList<Participant> participants = new ArrayList<>();

    @Test
    public void return_zero_if_one_participant_checked_in_before_21_pm() {
        participants.add(EARLY_PARTICIPANT);
        Restaurant restaurant = new Restaurant(participants);
        int coldMeals = restaurant.getColdMeals();
        assertThat(coldMeals).isEqualTo(0);
    }

    @Test
    public void return_one_if_one_participant_checked_in_after_21_pm() {
        participants.add(LATE_PARTICIPANT);
        Restaurant restaurant = new Restaurant(participants);
        int coldMeals = restaurant.getColdMeals();
        assertThat(coldMeals).isEqualTo(1);
    }

    @Test
    public void return_one_if_two_participants_checked_in_but_only_one_after_21_pm() {
        participants.add(LATE_PARTICIPANT);
        participants.add(EARLY_PARTICIPANT);
        Restaurant restaurant = new Restaurant(participants);
        int coldMeals = restaurant.getColdMeals();
        assertThat(coldMeals).isEqualTo(1);
    }

    @Test
    public void return_two_if_two_participants_checked_in_after_21_pm() {
        participants.add(LATE_PARTICIPANT);
        participants.add(LATE_PARTICIPANT);
        Restaurant restaurant = new Restaurant(participants);
        int coldMeals = restaurant.getColdMeals();
        assertThat(coldMeals).isEqualTo(2);
    }

    @Test
    public void return_zero_if_two_participants_checked_in_before_21_pm() {
        participants.add(EARLY_PARTICIPANT);
        participants.add(EARLY_PARTICIPANT);
        Restaurant restaurant = new Restaurant(participants);
        int coldMeals = restaurant.getColdMeals();
        assertThat(coldMeals).isEqualTo(0);
    }

}

class GetMealByDietShould {
    private static final Participant PARTICIPANT_OMNIVOROUS = new Participant(LocalTime.of(20, 0));
    private static final Participant PARTICIPANT_VEGETARIAN = new Participant(LocalTime.of(20, 0), Diet.VEGETARIAN);
    private static final Participant LATE_PARTICIPANT = new Participant(LocalTime.of(22, 0));
    public static final Participant PARTICIPANT_PESCATARIAN = new Participant(LocalTime.of(20, 0), Diet.PESCATARIAN);
    public static final Participant PARTICIPANT_VEGAN = new Participant(LocalTime.of(20, 0), Diet.VEGAN);
    public static final Participant LATE_PARTICIPANT_VEGETARIAN = new Participant(LocalTime.of(22, 0), Diet.VEGETARIAN);

    private List<Participant> participants = new ArrayList<>();

    @Test
    void return_one_if_there_is_a_participant() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        Meals meals = new Meals((new ArrayList<>()));
        meals.add(MealBuilder.aMeal().withOmnivorous(1).build());
        Restaurant restaurant = new Restaurant(participants);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_zero_if_one_participant_but_no_meal() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        Meals meals = new Meals((new ArrayList<>()));
        Restaurant restaurant = new Restaurant(participants, 0);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_twelve_if_two_participant_and_six_meals() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        participants.add(PARTICIPANT_OMNIVOROUS);
        Meals meals = new Meals((new ArrayList<>()));
        meals.add(MealBuilder.aMeal().withOmnivorous(2).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(2).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(2).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(2).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(2).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(2).build());
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_six_omnivorous_covers_for_one_participant_and_six_meals() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        Meals meals = new Meals((new ArrayList<>()));
        meals.add(MealBuilder.aMeal().withOmnivorous(1).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(1).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(1).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(1).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(1).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(1).build());
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_six_vegetarian_covers_for_one_vegetarian_participant_and_six_meals() {
        participants.add(PARTICIPANT_VEGETARIAN);
        Meals meals = new Meals((new ArrayList<>()));
        meals.add(MealBuilder.aMeal().withVegetarians(1).build());
        meals.add(MealBuilder.aMeal().withVegetarians(1).build());
        meals.add(MealBuilder.aMeal().withVegetarians(1).build());
        meals.add(MealBuilder.aMeal().withVegetarians(1).build());
        meals.add(MealBuilder.aMeal().withVegetarians(1).build());
        meals.add(MealBuilder.aMeal().withVegetarians(1).build());
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_six_vegetarian_covers_and_six_omnivorous_for_two_participant_and_six_meals() {
        participants.add(PARTICIPANT_VEGETARIAN);
        participants.add(PARTICIPANT_OMNIVOROUS);
        Meals meals = new Meals((new ArrayList<>()));
        meals.add(MealBuilder.aMeal().withOmnivorous(1).withVegetarians(1).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(1).withVegetarians(1).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(1).withVegetarians(1).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(1).withVegetarians(1).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(1).withVegetarians(1).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(1).withVegetarians(1).build());
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_five_omnivorous_cover_for_one_late_participant_for_6_meals() {
        participants.add(LATE_PARTICIPANT);
        Meals meals = new Meals((new ArrayList<>()));
        meals.add(MealBuilder.aMeal().build());
        meals.add(MealBuilder.aMeal().withOmnivorous(1).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(1).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(1).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(1).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(1).build());
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_eleven_omnivorous_cover_for_one_late_participant_and_another_on_time_for_6_meals() {
        participants.add(LATE_PARTICIPANT);
        participants.add(PARTICIPANT_OMNIVOROUS);
        Meals meals = new Meals((new ArrayList<>()));
        meals.add(MealBuilder.aMeal().withOmnivorous(1).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(2).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(2).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(2).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(2).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(2).build());
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_three_omnivorous_covers_for_the_second_meal_and_one_omnivorous_for_three_participant_and_two_meals() {
        participants.add(LATE_PARTICIPANT);
        participants.add(LATE_PARTICIPANT);
        participants.add(PARTICIPANT_OMNIVOROUS);

        Meals meals = new Meals((new ArrayList<>()));
        meals.add(MealBuilder.aMeal().withOmnivorous(1).build());
        meals.add(MealBuilder.aMeal().withOmnivorous(3).build());

        Restaurant restaurant = new Restaurant(participants, 2);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_list_of_meals_for_participants_with_all_characteristics() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        participants.add(PARTICIPANT_VEGETARIAN);
        participants.add(PARTICIPANT_PESCATARIAN);
        participants.add(PARTICIPANT_VEGAN);

        participants.add(LATE_PARTICIPANT);
        participants.add(LATE_PARTICIPANT_VEGETARIAN);

        Restaurant restaurant = new Restaurant(participants, 3);

        Meals meals = new Meals((new ArrayList<>()));

        Meal firstMeal = MealBuilder.aMeal()
            .withOmnivorous(1)
            .withVegetarians(1)
            .withVegans(1)
            .withPescatarians(1)
            .build();

        meals.add(firstMeal);

        Meal secondMeal = MealBuilder.aMeal()
            .withOmnivorous(2)
            .withVegetarians(2)
            .withVegans(1)
            .withPescatarians(1)
            .build();

        meals.add(secondMeal);

        Meal thirdMeal = MealBuilder.aMeal()
                                .withOmnivorous(2)
                                .withVegetarians(2)
                                .withVegans(1)
                                .withPescatarians(1)
                                .build();
        meals.add(thirdMeal);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_meal_for_thursday_night() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        participants.add(PARTICIPANT_OMNIVOROUS);

        Meal mealThursdayNight = MealBuilder.aMeal().withOmnivorous(2).build();

        Restaurant restaurant = new Restaurant(participants);

        assertThat(restaurant.getMeal("Thursday Night")).isEqualTo(mealThursdayNight);
    }
    @Test
    void return_meal_for_friday_noon() {
        participants.add(LATE_PARTICIPANT);
        participants.add(PARTICIPANT_OMNIVOROUS);

        Meal mealThursdayNight = MealBuilder.aMeal().withOmnivorous(2).build();

        Restaurant restaurant = new Restaurant(participants, 4);

        assertThat(restaurant.getMeal("Friday Noon")).isEqualTo(mealThursdayNight);
    }

    public static final class MealBuilder {
        private long vegetarians = 0;
        private long vegans = 0;
        private long pescatarians = 0;

        private long omnivorous = 0;

        private MealBuilder() {
        }

        public static MealBuilder aMeal() {
            return new MealBuilder();
        }

        public MealBuilder withVegetarians(long vegetarians) {
            this.vegetarians = vegetarians;
            return this;
        }

        public MealBuilder withVegans(long vegans) {
            this.vegans = vegans;
            return this;
        }

        public MealBuilder withPescatarians(long pescatarians) {
            this.pescatarians = pescatarians;
            return this;
        }

        public MealBuilder withOmnivorous(long omnivorous) {
            this.omnivorous = omnivorous;
            return this;
        }

        public Meal build() {

            Map<Diet, Long> coversByDiet = new HashMap<>();
            coversByDiet.put(Diet.VEGAN, this.vegans);
            coversByDiet.put(Diet.OMNIVOROUS, this.omnivorous);
            coversByDiet.put(Diet.PESCATARIAN, this.pescatarians);
            coversByDiet.put(Diet.VEGETARIAN, this.vegetarians);

            Stream.of(Diet.values()).forEach(diet -> coversByDiet.computeIfAbsent(diet, c -> 0L));
            return new Meal(coversByDiet);
        }
    }
}



