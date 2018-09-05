package fr.lacombe.magic6tem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Covers {
    private final Map<Diet, Integer> covers = new HashMap<>();

    public void addDietCovers(List<Participant> participants, int numberOfMeal, Diet diet) {
        int countOfDiet = (int) participants.stream().filter(participant -> participant.dietIs(diet)).count();
        if (countOfDiet != 0) {
            int countOfDietForAllDays = countOfDiet * numberOfMeal - (int) participants.stream()
                    .filter(participant -> participant.dietIs(diet))
                    .filter(participant -> participant.isLateFor(Restaurant.THURSDAY_COLD_DINER_BEGINNING_HOUR))
                    .count();
            covers.put(diet, countOfDietForAllDays);
        }
    }

    public Integer countOf(Diet diet) {
        return covers.getOrDefault(diet, 0);
    }

    public List<DietCount> countOfCoversByDietFor(List<Participant> participants) {
        return participants.stream()
                .map(Participant::getDiet)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(dietLongEntry -> new DietCount(dietLongEntry.getKey(), dietLongEntry.getValue()))
                .collect(Collectors.toList());

    }

    @Override
    public String toString() {
        return "Covers{" +
                "\ncovers=" + covers +
                '}';
    }
}
