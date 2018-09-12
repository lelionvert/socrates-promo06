package fr.lacombe.magic6tem.cover;

import fr.lacombe.magic6tem.Meal;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class MealTest {

    @Nested
    class MealFactoryShould {
        @Test
        void return_Meal_with_only_one_vegan_cover_when_given_vegan_participant() {
            List<Participant> participants = new ArrayList<>();
            Participant participantVegan = new Participant(Diet.VEGAN);
            participants.add(participantVegan);

            List<Diet> dietsVegan = new ArrayList<>();

            CoversFactory coversFactory = new CoversFactory() {
                @Override
                public Covers create(List<Diet> diets) {
                        dietsVegan.addAll(diets);
                        return Covers.from(new ArrayList<>());
                    }
            };
            Meal.from(participants, coversFactory);

            assertThat(dietsVegan).containsExactly(Diet.VEGAN);
        }

        @Test
        void toto() {
            List<Participant> participants = new ArrayList<>();
            Participant participantVegan = new Participant(Diet.VEGAN);
            participants.add(participantVegan);


            CoversFactory coversFactory = Mockito.mock(CoversFactory.class);

            Meal.from(participants, coversFactory);

            final ArrayList<Diet> diets = new ArrayList<>();
            diets.add(Diet.VEGAN);
            Mockito.verify(coversFactory).create(diets);
        }
    }


}
