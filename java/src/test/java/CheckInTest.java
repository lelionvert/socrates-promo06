import fr.lacombe.magic6tem.CheckIn;
import fr.lacombe.magic6tem.Meal;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CheckInTest {

    @Test
    void given_an_empty_checkin_list_return_zero_cold_meal() {
        List<CheckIn> checkIns = new ArrayList<>();
        Meal meal = new Meal(checkIns);
        assertThat(meal.getCountOfColdMeal()).isEqualTo(0);
    }

    @Test
    void given_a_checkin_list_with_one_checkin_with_arrival_date_after_nine_pm_return_one_cold_meal() {
        List<CheckIn> checkIns = new ArrayList<>();
        CheckIn checkIn = new CheckIn(Meal.THURSDAY_COLD_DINER_BEGINNING_HOUR.plusMinutes(1));
        checkIns.add(checkIn);
        Meal meal = new Meal(checkIns);

        int countOfColdMeal = meal.getCountOfColdMeal();


        assertThat(countOfColdMeal).isEqualTo(1);
    }

    @Test
    void given_a_checkin_list_with_one_checkin_with_arrival_date_before_nine_pm_return_zero_cold_meal() {
        List<CheckIn> checkIns = new ArrayList<>();
        CheckIn checkIn = new CheckIn(Meal.THURSDAY_COLD_DINER_BEGINNING_HOUR.minusMinutes(1));
        checkIns.add(checkIn);
        Meal meal = new Meal(checkIns);

        int countOfColdMeal = meal.getCountOfColdMeal();

        assertThat(countOfColdMeal).isEqualTo(0);
    }
    @Test
    void given_a_checkin_list_with_two_checkin_with_arrival_date_after_nine_pm_return_2_cold_meals() {
        List<CheckIn> checkIns = new ArrayList<>();
        CheckIn checkIn = new CheckIn(Meal.THURSDAY_COLD_DINER_BEGINNING_HOUR.plusMinutes(1));
        checkIns.add(checkIn);
        checkIns.add(checkIn);
        Meal meal = new Meal(checkIns);


        int countOfColdMeal = meal.getCountOfColdMeal();

        assertThat(countOfColdMeal).isEqualTo(2);
    }

    @Test
    void given_a_five_checkins_list_with_two_arrival_time_date_after_nine_pm_return_2_cold_meals() {
        List<CheckIn> checkIns = new ArrayList<>();
        CheckIn checkInAfterNinePM = new CheckIn(Meal.THURSDAY_COLD_DINER_BEGINNING_HOUR.plusMinutes(1));
        CheckIn checkInBeforeNinePM = new CheckIn(Meal.THURSDAY_COLD_DINER_BEGINNING_HOUR.minusMinutes(1));
        checkIns.add(checkInAfterNinePM);
        checkIns.add(checkInAfterNinePM);
        checkIns.add(checkInBeforeNinePM);
        checkIns.add(checkInBeforeNinePM);
        checkIns.add(checkInBeforeNinePM);
        Meal meal = new Meal(checkIns);


        int countOfColdMeal = meal.getCountOfColdMeal();

        assertThat(countOfColdMeal).isEqualTo(2);
    }
}