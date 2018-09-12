package fr.lacombe.magic6tem.cover;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class CoverTest {

    @Nested
    @DisplayName("Get count of covers By Diet For Participants Should")
    class CountOfCoversByDietFor {

        @Test
        void return_list_one_cover_vegetarian_when_only_one_vegetarian_participant() {
            Participant participantVege = new Participant(Diet.VEGETARIAN);
            List<Participant> participants = Collections.singletonList(participantVege);
            DietCount dietCount = new DietCount(Diet.VEGETARIAN,1L);

            List<Diet> participantsDiet = participants.stream()
                    .map(Participant::getDiet).collect(Collectors.toList());
            Covers covers = Covers.from(participantsDiet);

            assertThat(covers.contains(dietCount)).isTrue();
        }

        @Test
        void return_list_one_cover_omnivore_when_only_one_omnivore_participant() {
            Participant participantOmnivore = new Participant(Diet.OMNIVORE);
            List<Participant> participants = Collections.singletonList(participantOmnivore);
            DietCount dietCountOmnivore = new DietCount(Diet.OMNIVORE,1L);

            List<Diet> participantsDiet = participants.stream()
                    .map(Participant::getDiet).collect(Collectors.toList());
            Covers covers = Covers.from(participantsDiet);

            assertThat(covers.contains(dietCountOmnivore)).isTrue();

        }

        @Test
        void return_list_two_cover_omnivore_when_two_omnivore_participants() {
            Participant participantOmnivore = new Participant(Diet.OMNIVORE);
            List<Participant> participants = Arrays.asList(participantOmnivore,participantOmnivore);
            DietCount dietCountOmnivore = new DietCount(Diet.OMNIVORE,2L);

            List<Diet> participantsDiet = participants.stream()
                    .map(Participant::getDiet).collect(Collectors.toList());
            Covers covers = Covers.from(participantsDiet);

            assertThat(covers.contains(dietCountOmnivore)).isTrue();

        }

        @Test
        void return_covers_from_a_list_of_participants_with_different_diets() {
            Participant participantOmnivore = new Participant(Diet.OMNIVORE);
            Participant participantVegan = new Participant(Diet.VEGAN);
            Participant participantVegetarian = new Participant(Diet.VEGETARIAN);
            Participant participantPescatarian = new Participant(Diet.PESCATARIAN);

            List<Participant> participants = Arrays.asList(participantOmnivore,participantOmnivore, participantPescatarian, participantVegetarian, participantVegan, participantVegan);

            DietCount dietCountOmnivore = new DietCount(Diet.OMNIVORE,2L);
            DietCount dietCountPescatarian = new DietCount(Diet.PESCATARIAN,1L);
            DietCount dietCountVegetarian = new DietCount(Diet.VEGETARIAN,1L);
            DietCount dietCountVegan = new DietCount(Diet.VEGAN,2L);

            List<Diet> participantsDiet = participants.stream()
                    .map(Participant::getDiet).collect(Collectors.toList());
            Covers covers = Covers.from(participantsDiet);

            assertThat(covers.contains(dietCountOmnivore)).isTrue();
           assertThat(covers.contains(dietCountPescatarian)).isTrue();
            assertThat(covers.contains(dietCountVegetarian)).isTrue();
            assertThat(covers.contains(dietCountVegan)).isTrue();

        }

        @Test
        void return_list_zero_cover_when_no_participants() {
            List<Participant> participants = new ArrayList<>();

            List<Diet> participantsDiet = participants.stream()
                                                .map(Participant::getDiet)
                                                .collect(Collectors.toList());
            Covers covers = Covers.from(participantsDiet);

            assertThat(covers.isEmpty()).isTrue();
        }

    }

    @Nested
    class CountOfCoversByDietForAllMeals {

//        @Test
//        void return_Meal_with_only_one_vegan_cover_when_given_vegan_participant() {
//            Participant participantVege = new Participant(Diet.VEGETARIAN);
//            List<Participant> participants = Collections.singletonList(participantVege);
//            Covers covers = new Covers();
//            assertThat(covers.countOfCoversByDietForAllMeals(participants)).contains(new DietCount(Diet.VEGETARIAN,6L));
//        }

    }

}
