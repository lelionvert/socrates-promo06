package fr.lacombe.magic6tem;

public class Participant {

    private Diet diet;

    public Participant(Diet diet) {
        this.diet = diet;
    }

    public boolean dietIs(Diet diet) {
        return this.diet.equals(diet);
    }
}
