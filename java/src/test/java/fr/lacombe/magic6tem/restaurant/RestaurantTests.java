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

class GetCoversByDietShould {
    private static final Participant PARTICIPANT_OMNIVOROUS = new Participant(LocalTime.of(20, 0));
    private static final Participant PARTICIPANT_VEGETARIAN = new Participant(LocalTime.of(20, 0), Diet.VEGETARIAN);
    private static final Participant LATE_PARTICIPANT = new Participant(LocalTime.of(22, 0));

    private List<Participant> participants = new ArrayList<>();

    @Test
    void return_one_if_there_is_a_participant() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        List<Covers> covers = new ArrayList<>();
        covers.add(new Covers(1));
        Restaurant restaurant = new Restaurant(participants);
        assertThat(restaurant.getCoversByDiet2()).isEqualTo(covers);
    }

    @Test
    void return_zero_if_one_participant_but_no_meal() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        List<Covers> covers = new ArrayList<>();
        Restaurant restaurant = new Restaurant(participants, 0);
        assertThat(restaurant.getCoversByDiet2()).isEqualTo(covers);
    }

    @Test
    void return_twelve_if_two_participant_and_six_meals() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        participants.add(PARTICIPANT_OMNIVOROUS);
        List<Covers> covers = new ArrayList<>();
        covers.add(new Covers(2));
        covers.add(new Covers(2));
        covers.add(new Covers(2));
        covers.add(new Covers(2));
        covers.add(new Covers(2));
        covers.add(new Covers(2));
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getCoversByDiet2()).isEqualTo(covers);
    }

    @Test
    void return_six_omnivorous_cover_for_one_participant_and_six_meals() {
        participants.add(PARTICIPANT_OMNIVOROUS);
        List<Covers> covers = new ArrayList<>();
        covers.add(new Covers(1));
        covers.add(new Covers(1));
        covers.add(new Covers(1));
        covers.add(new Covers(1));
        covers.add(new Covers(1));
        covers.add(new Covers(1));
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getCoversByDiet2()).isEqualTo(covers);
    }

    @Test
    void return_six_vegetarian_covers_for_one_vegetarian_participant_and_six_meals() {
        participants.add(PARTICIPANT_VEGETARIAN);
        List<Covers> covers = new ArrayList<>();
        covers.add(new Covers(0, 1));
        covers.add(new Covers(0, 1));
        covers.add(new Covers(0, 1));
        covers.add(new Covers(0, 1));
        covers.add(new Covers(0, 1));
        covers.add(new Covers(0, 1));
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getCoversByDiet2()).isEqualTo(covers);
    }

    @Test
    void return_six_vegetarian_covers_and_six_omnivorous_for_two_participant_and_six_meals() {
        participants.add(PARTICIPANT_VEGETARIAN);
        participants.add(PARTICIPANT_OMNIVOROUS);
        List<Covers> covers = new ArrayList<>();
        covers.add(new Covers(1, 1));
        covers.add(new Covers(1, 1));
        covers.add(new Covers(1, 1));
        covers.add(new Covers(1, 1));
        covers.add(new Covers(1, 1));
        covers.add(new Covers(1, 1));
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getCoversByDiet2()).isEqualTo(covers);
    }

    @Test
    void return_five_omnivorous_cover_for_one_late_participant_for_6_meals() {
        participants.add(LATE_PARTICIPANT);
        List<Covers> covers = new ArrayList<>();
        covers.add(new Covers(0));
        covers.add(new Covers(1));
        covers.add(new Covers(1));
        covers.add(new Covers(1));
        covers.add(new Covers(1));
        covers.add(new Covers(1));
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getCoversByDiet2()).isEqualTo(covers);
    }

    @Test
    void return_eleven_omnivorous_cover_for_one_late_participant_and_another_on_time_for_6_meals() {
        participants.add(LATE_PARTICIPANT);
        participants.add(PARTICIPANT_OMNIVOROUS);
        List<Covers> covers = new ArrayList<>();
        covers.add(new Covers(1));
        covers.add(new Covers(2));
        covers.add(new Covers(2));
        covers.add(new Covers(2));
        covers.add(new Covers(2));
        covers.add(new Covers(2));
        Restaurant restaurant = new Restaurant(participants, 6);
        assertThat(restaurant.getCoversByDiet2()).isEqualTo(covers);
    }

    @Test
    void toto() {
        participants.add(LATE_PARTICIPANT);
        participants.add(LATE_PARTICIPANT);
        participants.add(PARTICIPANT_OMNIVOROUS);
        List<Covers> covers = new ArrayList<>();
        covers.add(new Covers(1));
        covers.add(new Covers(3));
        Restaurant restaurant = new Restaurant(participants, 2);
        assertThat(restaurant.getCoversByDiet2()).isEqualTo(covers);
    }
}



