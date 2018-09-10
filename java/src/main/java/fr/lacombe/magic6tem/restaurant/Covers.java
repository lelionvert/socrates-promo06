package fr.lacombe.magic6tem.restaurant;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Covers {

    private final Map<Diet, Long> coversByDiet;

    protected Covers(Map<Diet, Long> coversByDiet){

        this.coversByDiet = coversByDiet;
    }

    public static Covers from(List<Diet> diets) {
        Map<Diet, Long> coversByDiet;

        coversByDiet = diets.stream()
            .collect(Collectors.groupingBy(diet -> diet, Collectors.counting()));


        Stream.of(Diet.values()).forEach(diet -> coversByDiet.computeIfAbsent(diet,c -> 0L));

        return new Covers(coversByDiet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Covers covers = (Covers) o;
        return Objects.equals(coversByDiet, covers.coversByDiet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(coversByDiet);
    }

    @Override
    public String toString() {
        return "Covers{" +
            "coversByDiet=" + coversByDiet +
            '}';
    }

}
