package fr.lacombe.magic6tem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Covers {
    private final Map<Diet, Integer> covers = new HashMap<>();

    public void addDietCovers(List<Participant> participants, int numberOfMeal, Diet diet) {
        int countOfDiet = (int) participants.stream().filter(participant -> participant.dietIs(diet)).count();
        if (countOfDiet != 0) {
            covers.put(diet, countOfDiet * numberOfMeal);
        }
    }

    Integer countOf(Diet diet) {
        return covers.getOrDefault(diet,0);
    }
}
