package fr.lacombe.magic6tem.restaurant;

import fr.lacombe.magic6tem.Participant;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fr.lacombe.magic6tem.restaurant.Diet.OMNIVOROUS;
import static fr.lacombe.magic6tem.restaurant.Diet.VEGETARIAN;
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

class GetCoversByDietShould {
    private static final Participant PARTICIPANT_OMNIVOROUS = new Participant(LocalTime.of(20, 0));
    public static final Participant PARTICIPANT_VEGETARIAN = new Participant(LocalTime.of(20, 0), Diet.VEGETARIAN);

    private List<Participant> participants = new ArrayList<>();
    public static final List<Diet> SIX_OMNIVOROUS_COVERS = Arrays.asList(OMNIVOROUS, OMNIVOROUS, OMNIVOROUS, OMNIVOROUS, OMNIVOROUS, OMNIVOROUS);
    public static final List<Diet> SIX_VEGETARIAN_COVERS = Arrays.asList(VEGETARIAN, VEGETARIAN, VEGETARIAN, VEGETARIAN, VEGETARIAN, VEGETARIAN);
    public static final List<Diet> SIX_VEGETARIAN_COVERS_AND_SIX_OMNIVOROUS_COVERS = Stream.concat(SIX_VEGETARIAN_COVERS.stream(),
            SIX_OMNIVOROUS_COVERS.stream())
            .collect(Collectors.toList());

    @Test
    void return_zero_if_there_is_no_participant() {
        Restaurant restaurant = new Restaurant();
        assertThat(restaurant.getCoversByDiet().size()).isEqualTo(0);
    }

    @Test
    void return_one_if_there_is_a_participant() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        Restaurant restaurant = new Restaurant(participants);
        assertThat(restaurant.getCoversByDiet().size()).isEqualTo(1);
    }

    @Test
    void return_zero_if_one_participant_but_no_meal() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        Restaurant restaurant = new Restaurant(participants, 0);
        assertThat(restaurant.getCoversByDiet().size()).isEqualTo(0);
    }

    @Test
    void return_two_if_one_participant_and_two_meal() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        Restaurant restaurant = new Restaurant(participants, 2);
        assertThat(restaurant.getCoversByDiet().size()).isEqualTo(2);
    }

    @Test
    void return_twelve_if_two_participant_and_six_meal() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        participants.add(PARTICIPANT_OMNIVOROUS);
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getCoversByDiet().size()).isEqualTo(12);
    }

    @Test
    void return_six_omnivorous_cover_for_one_participant_and_six_meal() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getCoversByDiet()).isEqualTo(SIX_OMNIVOROUS_COVERS);
    }

    @Test
    void return_six_vegetarian_covers_for_one_participant_and_six_meals() {
        participants.add(PARTICIPANT_VEGETARIAN);
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getCoversByDiet()).isEqualTo(SIX_VEGETARIAN_COVERS);
    }

    @Test
    void return_six_vegetarian_covers_and_six_omnivorous_for_two_participant_and_six_meals() {
        participants.add(PARTICIPANT_VEGETARIAN);
        participants.add(PARTICIPANT_OMNIVOROUS);
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getCoversByDiet())
                .isEqualTo(SIX_VEGETARIAN_COVERS_AND_SIX_OMNIVOROUS_COVERS);
    }
}



