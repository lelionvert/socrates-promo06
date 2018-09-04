package fr.lacombe.magic6tem;

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
        Conference conference = new Conference(participants);
        int coldMeals = conference.getColdMeals();
        assertThat(coldMeals).isEqualTo(0);
    }

    @Test
    public void return_one_if_one_participant_checked_in_after_21_pm() {
        participants.add(LATE_PARTICIPANT);
        Conference conference = new Conference(participants);
        int coldMeals = conference.getColdMeals();
        assertThat(coldMeals).isEqualTo(1);
    }

    @Test
    public void return_one_if_two_participants_checked_in_but_only_one_after_21_pm() {
        participants.add(LATE_PARTICIPANT);
        participants.add(EARLY_PARTICIPANT);
        Conference conference = new Conference(participants);
        int coldMeals = conference.getColdMeals();
        assertThat(coldMeals).isEqualTo(1);
    }

    @Test
    public void return_two_if_two_participants_checked_in_after_21_pm() {
        participants.add(LATE_PARTICIPANT);
        participants.add(LATE_PARTICIPANT);
        Conference conference = new Conference(participants);
        int coldMeals = conference.getColdMeals();
        assertThat(coldMeals).isEqualTo(2);
    }

    @Test
    public void return_zero_if_two_participants_checked_in_before_21_pm() {
        participants.add(EARLY_PARTICIPANT);
        participants.add(EARLY_PARTICIPANT);
        Conference conference = new Conference(participants);
        int coldMeals = conference.getColdMeals();
        assertThat(coldMeals).isEqualTo(0);
    }

}

class GetCoversByDietShould {
    private static final Participant PARTICIPANT = new Participant(LocalTime.of(20, 0));
    private List<Participant> participants = new ArrayList<>();

    @Test
    void return_zero_if_there_is_no_participant() {
        Conference conference = new Conference();
        assertThat(conference.getCoversByDiet("O").size()).isEqualTo(0);
    }

    @Test
    void return_one_if_there_is_a_participant() {
        participants.add(PARTICIPANT);
        Conference conference = new Conference(participants);
        assertThat(conference.getCoversByDiet("O").size()).isEqualTo(1);
    }

    @Test
    void return_zero_if_one_participant_but_no_meal() {
        participants.add(PARTICIPANT);
        Conference conference = new Conference(participants, 0);
        assertThat(conference.getCoversByDiet("O").size()).isEqualTo(0);
    }

    @Test
    void return_two_if_one_participant_and_two_meal() {
        participants.add(PARTICIPANT);
        Conference conference = new Conference(participants, 2);
        assertThat(conference.getCoversByDiet("O").size()).isEqualTo(2);
    }

    @Test
    void return_twelve_if_two_participant_and_six_meal() {
        participants.add(PARTICIPANT);
        participants.add(PARTICIPANT);
        Conference conference = new Conference(participants, 6);
        assertThat(conference.getCoversByDiet("O").size()).isEqualTo(12);
    }

    @Test
    void return_six_omnivorous_cover_for_one_participant_and_six_meal() {
        participants.add(PARTICIPANT);
        List<String> coversList = new ArrayList<>();
        coversList.add("Omnivorous");
        coversList.add("Omnivorous");
        coversList.add("Omnivorous");
        coversList.add("Omnivorous");
        coversList.add("Omnivorous");
        coversList.add("Omnivorous");
        Conference conference = new Conference(participants, 6);
        assertThat(conference.getCoversByDiet("Omnivorous")).isEqualTo(coversList);
    }

}


