package fr.lacombe.magic6tem;

import java.time.LocalDateTime;
import java.util.List;

public class Restaurant {
    public static final LocalDateTime THURSDAY_COLD_DINER_BEGINNING_HOUR = LocalDateTime.of(2018, 10, 25, 21, 00);
    private List<CheckIn> checkIns;

    public Restaurant(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public static int getCountOfCovers(List<Participant> participant) {
        return participant.size();
    }

    public static int getCountOfCovers(List<Participant> participants, int numberOfMeal) {
        return 0;
    }

    public int getCountOfColdMeal() {
        return (int) checkIns.stream()
                .filter(checkIn -> checkIn.isAfter(THURSDAY_COLD_DINER_BEGINNING_HOUR))
                .count();
}

}
