package fr.lacombe.magic6tem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public class Restaurant {
    public static final LocalDateTime THURSDAY_COLD_DINER_BEGINNING_HOUR = LocalDateTime.of(2018, 10, 25, 21, 00);
    private List<CheckIn> checkIns;
    private Covers covers = new Covers();

    public Restaurant(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public Restaurant() {

    }

    public Covers coversFor(List<Participant> participants, int numberOfMeal) {
        Stream.of(Diet.values()).forEach(diet -> covers.addDietCovers(participants, numberOfMeal, diet));
        return covers;
    }

    public int getCountOfColdMeal() {
        return (int) checkIns.stream()
                .filter(checkIn -> checkIn.isAfter(THURSDAY_COLD_DINER_BEGINNING_HOUR))
                .count();
    }

}
