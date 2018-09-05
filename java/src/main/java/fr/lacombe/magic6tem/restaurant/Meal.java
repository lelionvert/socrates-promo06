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

    private Meal(Map<Diet, Long> coversByDiet){

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

    public static Builder aMeal() {
        return new Builder();
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

    public static final class Builder {
        private long vegetarians = 0;
        private long vegans = 0;
        private long pescatarians = 0;

        private long omnivorous = 0;

        private Builder() {
        }

        public Builder withVegetarians(long vegetarians) {
            this.vegetarians = vegetarians;
            return this;
        }

        public Builder withVegans(long vegans) {
            this.vegans = vegans;
            return this;
        }

        public Builder withPescatarians(long pescatarians) {
            this.pescatarians = pescatarians;
            return this;
        }

        public Builder withOmnivorous(long omnivorous) {
            this.omnivorous = omnivorous;
            return this;
        }

        public Meal build() {

            Map<Diet, Long> coversByDiet = new HashMap<>();
            coversByDiet.put(Diet.VEGAN, this.vegans);
            coversByDiet.put(Diet.OMNIVOROUS, this.omnivorous);
            coversByDiet.put(Diet.PESCATARIAN, this.pescatarians);
            coversByDiet.put(Diet.VEGETARIAN, this.vegetarians);
            return new Meal(coversByDiet);
        }
    }
}
