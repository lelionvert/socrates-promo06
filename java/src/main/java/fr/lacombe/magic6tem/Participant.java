package fr.lacombe.magic6tem;

import fr.lacombe.magic6tem.restaurant.Diet;

import java.time.LocalTime;
import java.util.Objects;

public class Participant {

    private LocalTime arrivalTime;
    private final Diet diet;

    public Participant(LocalTime arrivalTime) {
        this(arrivalTime,Diet.OMNIVOROUS);
    }

    public Participant(LocalTime arrivalTime, Diet diet) {
        this.arrivalTime = arrivalTime;
        this.diet = diet;
    }

    public static Participant ofArrivalTime(int hours, int minutes) {
        return new Participant(LocalTime.of(hours,minutes));
    }

    public boolean isArrivalTimeAfter(LocalTime limitHour) {
        return arrivalTime.isAfter(limitHour);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "arrivalTime=" + arrivalTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(arrivalTime, that.arrivalTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(arrivalTime);
    }

    public Diet getDiet() {
        return diet;
    }
}
