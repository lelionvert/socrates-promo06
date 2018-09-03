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
        LocalDateTime afterNinePM = LocalDateTime.of(2018, 10, 25,21,01);
        CheckIn checkIn = new CheckIn(afterNinePM);
        checkIns.add(checkIn);
        Meal meal = new Meal(checkIns);
        assertThat(meal.getCountOfColdMeal()).isEqualTo(1);
    }
}