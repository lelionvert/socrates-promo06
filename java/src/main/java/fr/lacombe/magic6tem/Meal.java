package fr.lacombe.magic6tem;

import java.time.LocalDateTime;
import java.util.List;

public class Meal {
    public static final LocalDateTime THURSDAY_NINE_PM = LocalDateTime.of(2018, 10, 25, 21, 00);
    private List<CheckIn> checkIns;

    public Meal(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public int getCountOfColdMeal() {
        return (int) checkIns.stream()
                .filter(x -> x.arrivalDateTime.isAfter(THURSDAY_NINE_PM))
                .count();
    }
}
