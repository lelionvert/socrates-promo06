package fr.lacombe.magic6tem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

class GetColdMealShould {

    @Test
    public void return_zero_if_one_checked_in_before_21_pm() {
        LocalTime arrivalDateTime = LocalTime.of(20, 0);
        Conference conference = new Conference(arrivalDateTime);
        int coldMeals = conference.getColdMeals();
        assertThat(coldMeals).isEqualTo(0);
    }
    @Test
    public void return_one_if_one_checked_in_after_21_pm() {
        LocalTime arrivalTime = LocalTime.of(22, 0);
        Conference conference = new Conference(arrivalTime);
        int coldMeals = conference.getColdMeals();
        assertThat(coldMeals).isEqualTo(1);
    }

}


