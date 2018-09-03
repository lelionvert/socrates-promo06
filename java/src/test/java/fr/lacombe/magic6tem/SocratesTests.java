package fr.lacombe.magic6tem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GetColdMealShould {

    @Test
    public void return_zero_if_one_checked_in_before_21_pm() {
        ArrayList<LocalTime> arrivalTimes = new ArrayList<>();
        arrivalTimes.add(LocalTime.of(20, 0));
        Conference conference = new Conference(arrivalTimes);
        int coldMeals = conference.getColdMeals();
        assertThat(coldMeals).isEqualTo(0);
    }
    @Test
    public void return_zero_if_one_participant_checked_in_before_21_pm() {
        Participant participant = new Participant(LocalTime.of(20, 0));
        Conference conference = new Conference(participant);
        int coldMeals = conference.getColdMeals2();
        assertThat(coldMeals).isEqualTo(0);
    }


    @Test
    public void return_one_if_one_checked_in_after_21_pm() {
        ArrayList<LocalTime> arrivalTimes = new ArrayList<>();
        arrivalTimes.add(LocalTime.of(22, 0));
        Conference conference = new Conference(arrivalTimes);
        int coldMeals = conference.getColdMeals();
        assertThat(coldMeals).isEqualTo(1);
    }

    @Test
    public void return_one_if_two_checked_in_but_only_one_after_21_pm() {
        ArrayList<LocalTime> arrivalTimes = new ArrayList<>();
        arrivalTimes.add(LocalTime.of(22, 0));
        arrivalTimes.add(LocalTime.of(20, 0));
        Conference conference = new Conference(arrivalTimes);
        int coldMeals = conference.getColdMeals();
        assertThat(coldMeals).isEqualTo(1);
    }

    @Test
    public void return_two_if_two_checked_in_after_21_pm() {
        ArrayList<LocalTime> arrivalTimes = new ArrayList<>();
        arrivalTimes.add(LocalTime.of(22, 0));
        arrivalTimes.add(LocalTime.of(22, 0));
        Conference conference = new Conference(arrivalTimes);
        int coldMeals = conference.getColdMeals();
        assertThat(coldMeals).isEqualTo(2);
    }

}


