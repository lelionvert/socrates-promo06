package fr.lacombe.magic6tem.restaurant;

import fr.lacombe.magic6tem.conference.Participant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Meal {

    private final Map<Diet, Long> coversByDiet;

    protected Meal(Map<Diet, Long> coversByDiet){

        this.coversByDiet = coversByDiet;
    }

    public static Meal from(List<Participant> participants) {
        Map<Diet, Long> coversByDiet;

        coversByDiet = participants.stream()
            .map(Participant::getDiet)
            .collect(Collectors.groupingBy(diet -> diet, Collectors.counting()));

        Stream.of(Diet.values()).forEach(diet -> coversByDiet.computeIfAbsent(diet,c -> 0L));

        return new Meal(coversByDiet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(coversByDiet, meal.coversByDiet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(coversByDiet);
    }

    @Override
    public String toString() {
        return "Meal{" +
            "coversByDiet=" + coversByDiet +
            '}';
    }

}
