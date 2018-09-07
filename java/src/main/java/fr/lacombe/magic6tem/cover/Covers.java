package fr.lacombe.magic6tem.cover;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Covers {

    List<DietCount> countOfCoversByDiet;

    private Covers(List<DietCount> countOfCoversByDiet) {
        this.countOfCoversByDiet = countOfCoversByDiet;
    }

    public static Covers from(List<Diet> diets) {
        List<DietCount> countOfCoversByDiet = diets.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(dietLongEntry -> new DietCount(dietLongEntry.getKey(), dietLongEntry.getValue()))
                .collect(Collectors.toList());
        return new Covers(countOfCoversByDiet);
    }

    public boolean contains(DietCount dietCount) {
        return this.countOfCoversByDiet.contains(dietCount);
    }

    public boolean isEmpty() {
        return this.countOfCoversByDiet.isEmpty();
    }
}
