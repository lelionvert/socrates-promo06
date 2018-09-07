package fr.lacombe.magic6tem.cover;

import java.util.Objects;

class DietCount {
    private final Diet diet;
    private final Long count;

    public DietCount(Diet diet, Long count) {

        this.diet = diet;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DietCount dietCount = (DietCount) o;
        return count == dietCount.count &&
                diet == dietCount.diet;
    }



    @Override
    public int hashCode() {

        return Objects.hash(diet, count);
    }

    @Override
    public String toString() {
        return "DietCount{" +
                "\ndiet=" + diet +
                ", \ncount=" + count +
                '}';
    }
}
