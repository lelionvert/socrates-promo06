package fr.lacombe.magic6tem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

class Covers {
    private final Map<Diet, Integer> covers = new HashMap<>();

    public void addDietCovers(List<Participant> participants, int numberOfMeal, Diet diet) {
        int countOfDiet = (int) participants.stream().filter(participant -> participant.dietIs(diet)).count();

        if (countOfDiet != 0) {
            Predicate<Participant> isLateForThursdayDinner = participant -> participant.isLateFor(Restaurant.THURSDAY_COLD_DINER_BEGINNING_HOUR);
            int countOfDietForAllDays = countOfDiet * numberOfMeal - (int) participants.stream().filter(participant -> participant.dietIs(diet)).filter(isLateForThursdayDinner).count();
            covers.put(diet, countOfDietForAllDays);
        }
    }

    Integer countOf(Diet diet) {
        return covers.getOrDefault(diet,0);
    }

    @Override
    public String toString() {
        return "Covers{" +
                "\ncovers=" + covers +
                '}';
    }
}
