package fr.lacombe.magic6tem.cover;

import java.util.*;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Covers covers = (Covers) o;
        return Objects.equals(countOfCoversByDiet, covers.countOfCoversByDiet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfCoversByDiet);
    }
}
