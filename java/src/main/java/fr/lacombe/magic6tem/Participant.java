package fr.lacombe.magic6tem;

import java.time.LocalDateTime;

public class Participant {

    private Diet diet;
    private final CheckIn checkIn;

    public Participant(Diet diet) {
        this.diet = diet;
        this.checkIn = CheckIn.of(LocalDateTime.of(2018,10,25,20,0));
    }

    public Participant(Diet omnivore, CheckIn checkIn) {
        diet = omnivore;
        this.checkIn = checkIn;
    }

    public boolean dietIs(Diet diet) {
        return this.diet.equals(diet);
    }

    public boolean isLateFor(LocalDateTime dateTime) {
        return checkIn.isAfter(dateTime);
    }
}
