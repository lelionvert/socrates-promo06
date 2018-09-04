package fr.lacombe.magic6tem;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant {
    public static final LocalDateTime THURSDAY_COLD_DINER_BEGINNING_HOUR = LocalDateTime.of(2018, 10, 25, 21, 00);
    private List<CheckIn> checkIns;

    public Restaurant(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public static int getCountOfCovers(List<Participant> participants, int numberOfMeal) {
        return participants.size() * numberOfMeal;
    }

    public static Map<Diet, Integer> getCountOfCoversBis(List<Participant> participants, int numberOfMeal) {
        Map<Diet, Integer> result = new HashMap<>();
        int veganCount = 0;
        int omnivoreCount = 0;
        int pescarianCount = 0;
        int vegetarianCount = 0;
        for (Participant participant : participants) {
            if (participant.dietIs(Diet.VEGAN)) {
                veganCount++;
            }
            if (participant.dietIs(Diet.OMNIVORE)) {
                omnivoreCount++;
            }
            if (participant.dietIs(Diet.PESCARIAN)) {
                pescarianCount++;
            }
            if (participant.dietIs(Diet.VEGETARIAN)) {
                vegetarianCount++;
            }
        }
        if (veganCount != 0) {
            result.put(Diet.VEGAN, veganCount * numberOfMeal);
        }
        if (omnivoreCount != 0) {
            result.put(Diet.OMNIVORE, omnivoreCount * numberOfMeal);
        }
        if (pescarianCount != 0) {
            result.put(Diet.PESCARIAN, pescarianCount * numberOfMeal);
        }
        if (vegetarianCount != 0) {
            result.put(Diet.VEGETARIAN, vegetarianCount * numberOfMeal);
        }
        return result;
    }

    public int getCountOfColdMeal() {
        return (int) checkIns.stream()
                .filter(checkIn -> checkIn.isAfter(THURSDAY_COLD_DINER_BEGINNING_HOUR))
                .count();
    }

}
