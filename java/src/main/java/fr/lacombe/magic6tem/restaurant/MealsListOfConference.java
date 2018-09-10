package fr.lacombe.magic6tem.restaurant;

import java.util.ArrayList;
import java.util.Objects;

class MealsListOfConference {

    private ArrayList<Covers> covers;

    public MealsListOfConference(ArrayList<Covers> covers) {

        this.covers = covers;
    }

    public MealsListOfConference() {
        this.covers = new ArrayList<>();
    }

    public void add(Covers covers) {
        this.covers.add(covers);
    }
    public Covers getMealByIndex(int index){
        return covers.get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealsListOfConference meals1 = (MealsListOfConference) o;
        return Objects.equals(covers, meals1.covers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(covers);
    }
}
