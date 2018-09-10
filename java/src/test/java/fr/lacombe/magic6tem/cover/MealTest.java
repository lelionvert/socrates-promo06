package fr.lacombe.magic6tem.cover;

import fr.lacombe.magic6tem.Meal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class MealTest {


    @Test
    void name() {
        List<Participant> participants = new ArrayList<>();
        Participant participantVegan = new Participant(Diet.VEGAN);
        participants.add(participantVegan);

        Meal mealFridayNight = new Meal(participants);

        DietCount dietCountOmnivore = new DietCount(Diet.OMNIVORE,2L);
        List<Diet> participantsDiet = participants.stream()
                .map(Participant::getDiet).collect(Collectors.toList());
        Covers covers = Covers.from(participantsDiet);


        Meal mealMocked;
        assertThat(mealFridayNight).isEqualTo(mealFridayNight);
    }
}
