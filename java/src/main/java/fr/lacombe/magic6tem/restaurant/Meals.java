package fr.lacombe.magic6tem.restaurant;

import java.util.ArrayList;
import java.util.Objects;

class Meals {

    private ArrayList<Meal> meals;

    public Meals(ArrayList<Meal> meals) {

        this.meals = meals;
    }

    public Meals() {
        this.meals = new ArrayList<>();
    }

    public void add(Meal meal) {
        this.meals.add(meal);
    }
    public Meal getMealByIndex(int index){
        return meals.get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meals meals1 = (Meals) o;
        return Objects.equals(meals, meals1.meals);
    }

    @Override
    public int hashCode() {

        return Objects.hash(meals);
    }
}
