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
}