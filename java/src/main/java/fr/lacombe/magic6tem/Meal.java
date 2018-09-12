package fr.lacombe.magic6tem;

import fr.lacombe.magic6tem.cover.Covers;
import fr.lacombe.magic6tem.cover.CoversFactory;
import fr.lacombe.magic6tem.cover.Diet;
import fr.lacombe.magic6tem.cover.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Meal {


    private final Covers covers;

    private Meal(Covers covers) {

        this.covers = covers;
    }

    public static Meal from(List<Participant> participants, CoversFactory coversFactory) {
        List<Diet> participantsDiet = participants.stream()
                .map(Participant::getDiet).collect(Collectors.toList());

        Covers covers = coversFactory.create(participantsDiet);

        return new Meal(covers);
    }

    public Covers getCovers() {
        return this.covers;
    }
}
