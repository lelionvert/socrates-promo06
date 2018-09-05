package fr.lacombe.magic6tem.cover;

import fr.lacombe.magic6tem.coldmeal.ColdMeal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Covers {
    private final Map<Diet, Integer> covers = new HashMap<>();
    private int numberOfMeal;

    public Covers(int numberOfMeal) {
        this.numberOfMeal = numberOfMeal;
    }

    public Covers() {

    }


    public static Covers coversFor(List<Participant> participants, int numberOfMeal) {
        Covers covers = new Covers(numberOfMeal);
        covers.countOfCoversByDietFor(participants);
        Stream.of(Diet.values()).forEach(diet -> {
            int countOfDiet = (int) participants.stream().filter(participant -> participant.dietIs(diet)).count();
            if (countOfDiet != 0) {
                int countOfDietForAllDays = countOfDiet * covers.numberOfMeal - (int) participants.stream()
                        .filter(participant -> participant.dietIs(diet))
                        .filter(participant -> participant.isLateFor(ColdMeal.THURSDAY_COLD_DINER_BEGINNING_HOUR))
                        .count();
                covers.covers.put(diet, countOfDietForAllDays);
            }
        });
        return covers;
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
