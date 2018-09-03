package fr.lacombe.magic6tem;

import java.util.List;

public class Meal {

    private List<CheckIn> checkIns;

    public Meal(List<CheckIn> checkIns) {

        this.checkIns = checkIns;
    }

    public int getCountOfColdMeal() {
        return 0;
    }
}
