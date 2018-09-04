package fr.lacombe.magic6tem.restaurant;

import fr.lacombe.magic6tem.conference.Participant;

import java.util.List;
import java.util.Objects;

public class Covers {

    private long vegetarians = 0;
    private long vegans = 0;
    private long pescatarians = 0;
    private long omnivorous = 0;

    public Covers(int omnivorous) {
        this.omnivorous = omnivorous;
    }

    public Covers() {
    }

    public Covers(int omnivorous, int vegetarians) {
        this.omnivorous = omnivorous;
        this.vegetarians = vegetarians;
    }

    public static Covers from(List<Participant> participants) {
        Covers covers = new Covers();
        covers.vegetarians = getCountForDiet(participants, Diet.VEGETARIAN);
        covers.vegans = getCountForDiet(participants, Diet.VEGAN);
        covers.pescatarians = getCountForDiet(participants, Diet.PESCATARIAN);
        covers.omnivorous = getCountForDiet(participants, Diet.OMNIVOROUS);
        return covers;
    }

    private static long getCountForDiet(List<Participant> participants, Diet searchedDiet) {
        return participants.stream().map(Participant::getDiet)
                .filter(diet -> diet.equals(searchedDiet)).count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Covers covers = (Covers) o;
        return vegetarians == covers.vegetarians &&
                vegans == covers.vegans &&
                pescatarians == covers.pescatarians &&
                omnivorous == covers.omnivorous;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vegetarians, vegans, pescatarians, omnivorous);
    }
}
