package fr.lacombe.magic6tem.restaurant;

import fr.lacombe.magic6tem.conference.Participant;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(1));
        Restaurant restaurant = new Restaurant(participants);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_zero_if_one_participant_but_no_meal() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        List<Meal> meals = new ArrayList<>();
        Restaurant restaurant = new Restaurant(participants, 0);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_twelve_if_two_participant_and_six_meals() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        participants.add(PARTICIPANT_OMNIVOROUS);
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(2));
        meals.add(new Meal(2));
        meals.add(new Meal(2));
        meals.add(new Meal(2));
        meals.add(new Meal(2));
        meals.add(new Meal(2));
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_six_omnivorous_covers_for_one_participant_and_six_meals() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(1));
        meals.add(new Meal(1));
        meals.add(new Meal(1));
        meals.add(new Meal(1));
        meals.add(new Meal(1));
        meals.add(new Meal(1));
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_six_vegetarian_covers_for_one_vegetarian_participant_and_six_meals() {
        participants.add(PARTICIPANT_VEGETARIAN);
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(0, 1));
        meals.add(new Meal(0, 1));
        meals.add(new Meal(0, 1));
        meals.add(new Meal(0, 1));
        meals.add(new Meal(0, 1));
        meals.add(new Meal(0, 1));
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_six_vegetarian_covers_and_six_omnivorous_for_two_participant_and_six_meals() {
        participants.add(PARTICIPANT_VEGETARIAN);
        participants.add(PARTICIPANT_OMNIVOROUS);
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(1, 1));
        meals.add(new Meal(1, 1));
        meals.add(new Meal(1, 1));
        meals.add(new Meal(1, 1));
        meals.add(new Meal(1, 1));
        meals.add(new Meal(1, 1));
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_five_omnivorous_cover_for_one_late_participant_for_6_meals() {
        participants.add(LATE_PARTICIPANT);
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(0));
        meals.add(new Meal(1));
        meals.add(new Meal(1));
        meals.add(new Meal(1));
        meals.add(new Meal(1));
        meals.add(new Meal(1));
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_eleven_omnivorous_cover_for_one_late_participant_and_another_on_time_for_6_meals() {
        participants.add(LATE_PARTICIPANT);
        participants.add(PARTICIPANT_OMNIVOROUS);
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(1));
        meals.add(new Meal(2));
        meals.add(new Meal(2));
        meals.add(new Meal(2));
        meals.add(new Meal(2));
        meals.add(new Meal(2));
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }

    @Test
    void return_three_omnivorous_covers_for_the_second_meal_and_one_omnivorous_for_three_participant_and_two_meals() {
        participants.add(LATE_PARTICIPANT);
        participants.add(LATE_PARTICIPANT);
        participants.add(PARTICIPANT_OMNIVOROUS);
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(1));
        meals.add(new Meal(3));
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

        Restaurant restaurant = new Restaurant(participants, 2);

        List<Meal> meals = new ArrayList<>();

        Meal firstMeal = new Meal();
        firstMeal.addCoversByDiet(Diet.VEGETARIAN, 1);
        firstMeal.addCoversByDiet(Diet.OMNIVOROUS,1);
        firstMeal.addCoversByDiet(Diet.PESCATARIAN,1);
        firstMeal.addCoversByDiet(Diet.VEGAN,1);

        meals.add(firstMeal);

        Meal secondMeal = new Meal();
        secondMeal.addCoversByDiet(Diet.VEGETARIAN, 2);
        secondMeal.addCoversByDiet(Diet.OMNIVOROUS,2);
        secondMeal.addCoversByDiet(Diet.PESCATARIAN,1);
        secondMeal.addCoversByDiet(Diet.VEGAN,1);

        meals.add(secondMeal);

        assertThat(restaurant.getMealsByDiet()).isEqualTo(meals);
    }
}



