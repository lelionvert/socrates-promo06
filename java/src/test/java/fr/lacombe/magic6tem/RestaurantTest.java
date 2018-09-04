package fr.lacombe.magic6tem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantTest {

    @Nested
    @DisplayName("Cold Restaurant Feature Should ")
    class GetColdMeal{
        @Test
        void return_zero_cold_meal_given_no_checkin() {
            List<CheckIn> checkIns = new ArrayList<>();
            Restaurant meal = new Restaurant(checkIns);
            assertThat(meal.getCountOfColdMeal()).isEqualTo(0);
        }

        @Test
        void return_one_cold_meal_given_one_checkin_with_arrival_date_after_limit_hour() {
            List<CheckIn> checkIns = new ArrayList<>();
            CheckIn checkIn = CheckIn.of(Restaurant.THURSDAY_COLD_DINER_BEGINNING_HOUR.plusMinutes(1));
            checkIns.add(checkIn);
            Restaurant meal = new Restaurant(checkIns);

            int countOfColdMeal = meal.getCountOfColdMeal();


            assertThat(countOfColdMeal).isEqualTo(1);
        }

        @Test
        void return_zero_cold_meal_given_one_checkin_with_arrival_date_before_limit_hour() {
            List<CheckIn> checkIns = new ArrayList<>();
            CheckIn checkIn = CheckIn.of(Restaurant.THURSDAY_COLD_DINER_BEGINNING_HOUR.minusMinutes(1));
            checkIns.add(checkIn);
            Restaurant meal = new Restaurant(checkIns);

            int countOfColdMeal = meal.getCountOfColdMeal();

            assertThat(countOfColdMeal).isEqualTo(0);
        }

        @Test
        void return_2_cold_meals_given_two_checkin_with_arrival_date_after_limit_hour() {
            List<CheckIn> checkIns = new ArrayList<>();
            CheckIn checkIn = CheckIn.of(Restaurant.THURSDAY_COLD_DINER_BEGINNING_HOUR.plusMinutes(1));
            checkIns.add(checkIn);
            checkIns.add(checkIn);
            Restaurant meal = new Restaurant(checkIns);


            int countOfColdMeal = meal.getCountOfColdMeal();

            assertThat(countOfColdMeal).isEqualTo(2);
        }

        @Test
        void return_2_cold_meals_given_a_five_checkins_with_two_arrival_time_date_after_limit_hour() {
            List<CheckIn> checkIns = new ArrayList<>();
            CheckIn checkInAfterNinePM = CheckIn.of(Restaurant.THURSDAY_COLD_DINER_BEGINNING_HOUR.plusMinutes(1));
            CheckIn checkInBeforeNinePM = CheckIn.of(Restaurant.THURSDAY_COLD_DINER_BEGINNING_HOUR.minusMinutes(1));
            checkIns.add(checkInAfterNinePM);
            checkIns.add(checkInAfterNinePM);
            checkIns.add(checkInBeforeNinePM);
            checkIns.add(checkInBeforeNinePM);
            checkIns.add(checkInBeforeNinePM);
            Restaurant meal = new Restaurant(checkIns);


            int countOfColdMeal = meal.getCountOfColdMeal();

            assertThat(countOfColdMeal).isEqualTo(2);
        }
    }
}