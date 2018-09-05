package fr.lacombe.magic6tem.cover;

import fr.lacombe.magic6tem.CheckIn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class CoverTest {

    public static final Participant OMNIVORE_PARTICIPANT = new Participant(Diet.OMNIVORE);
    public static final Participant VEGAN_PARTICIPANT = new Participant(Diet.VEGAN);

    @Nested
    @DisplayName("Get count of covers should")
    class GetCountCover {

        @Test
        public void return_0_when_given_no_participant() {
            List<Participant> participants = new ArrayList<>();
            assertThat(((int) getActual(participants, 1, Diet.OMNIVORE))).isEqualTo(0);
        }

        @Test
        void return_1_given_one_participant() {
            List<Participant> participants = new ArrayList<>();
            participants.add(new Participant(Diet.OMNIVORE));
            assertThat((int) getActual(participants, 1, Diet.OMNIVORE)).isEqualTo(1);
        }

        @Test
        void return_2_given_2_participant() {
            List<Participant> participants = new ArrayList<>();
            participants.add(new Participant(Diet.OMNIVORE));
            participants.add(new Participant(Diet.OMNIVORE));
            assertThat((int) getActual(participants, 1, Diet.OMNIVORE)).isEqualTo(2);
        }

        @Test
        void return_0_given_1_participants_and_0_meal() {
            List<Participant> participants = new ArrayList<>();
            participants.add(new Participant(Diet.OMNIVORE));
            assertThat((int) getActual(participants, 0, Diet.OMNIVORE)).isEqualTo(0);
        }

        @Test
        void return_1_given_one_participant_and_1_meal() {
            List<Participant> participants = new ArrayList<>();
            participants.add(new Participant(Diet.OMNIVORE));
            assertThat((int) getActual(participants, 1, Diet.OMNIVORE)).isEqualTo(1);
        }

        @Test
        void return_6_given_one_participant_and_6_meals() {
            List<Participant> participants = new ArrayList<>();
            Diet omnivore = Diet.OMNIVORE;
            participants.add(new Participant(omnivore));
            int numberOfMeal = 6;
            assertThat((int) getActual(participants, numberOfMeal, omnivore)).isEqualTo(numberOfMeal);
        }

        @Test
        void return_covers_by_diet_for_vegan_and_omnivore() {
            List<Participant> participants = new ArrayList<>();
            participants.add(OMNIVORE_PARTICIPANT);
            participants.add(VEGAN_PARTICIPANT);

            int numberOfMeal = 6;
            Diet omnivore = Diet.OMNIVORE;
            assertThat(getActual(participants, numberOfMeal, omnivore)).isEqualTo(numberOfMeal);
            assertThat(getActual(participants, numberOfMeal, Diet.VEGAN)).isEqualTo(numberOfMeal);
        }

        @Test
        void return_covers_by_diet_for_all_diet_type() {
            List<Participant> participants = new ArrayList<>();
            participants.add(OMNIVORE_PARTICIPANT);
            participants.add(new Participant(Diet.VEGAN));
            participants.add(new Participant(Diet.VEGAN));
            participants.add(new Participant(Diet.VEGETARIAN));
            participants.add(new Participant(Diet.VEGETARIAN));
            participants.add(new Participant(Diet.VEGETARIAN));
            participants.add(new Participant(Diet.PESCATARIAN));
            participants.add(new Participant(Diet.PESCATARIAN));
            participants.add(new Participant(Diet.PESCATARIAN));
            participants.add(new Participant(Diet.PESCATARIAN));

            assertThat(getActual(participants, 6, Diet.OMNIVORE)).isEqualTo(6);
            assertThat(getActual(participants, 6, Diet.VEGAN)).isEqualTo(12);
            assertThat(getActual(participants, 6, Diet.VEGETARIAN)).isEqualTo(18);
            assertThat(getActual(participants, 6, Diet.PESCATARIAN)).isEqualTo(24);
        }

        @Test
        void return_five_covers_for_participant_who_is_late_for_6_meals() {

            List<Participant> participants = new ArrayList<>();
            CheckIn checkIn = CheckIn.of(LocalDateTime.of(2018, 10, 25, 21, 1));
            Participant lateOmnivoreParticipant = new Participant(Diet.OMNIVORE, checkIn);
            participants.add(lateOmnivoreParticipant);


            Integer countOfCover = getActual(participants, 6, Diet.OMNIVORE);

            assertThat(countOfCover).isEqualTo(5);
        }

        @Test
        void return_five_covers_for_participants_which_one_is_late_and_omni_other_vegan_and_on_time_for_6_meals() {

            List<Participant> participants = new ArrayList<>();
            CheckIn checkIn = CheckIn.of(LocalDateTime.of(2018, 10, 25, 21, 1));
            Participant lateOmnivoreParticipant = new Participant(Diet.OMNIVORE, checkIn);
            Participant veganParticipant = new Participant(Diet.VEGAN);
            participants.add(lateOmnivoreParticipant);
            participants.add(veganParticipant);


            Integer countOfCoverOmnivore = getActual(participants, 6, Diet.OMNIVORE);
            Integer countOfCoverVegan = getActual(participants, 6, Diet.VEGAN);

            assertThat(countOfCoverOmnivore).isEqualTo(5);
            assertThat(countOfCoverVegan).isEqualTo(6);
        }
    }

    private Integer getActual(List<Participant> participants, int numberOfMeal, Diet omnivore) {
        Covers covers = new Covers(numberOfMeal);
        List<DietCount> dietCounts = covers.countOfCoversByDietFor(participants);
        dietCounts.stream().filter(dietCount -> Diet.OMNIVORE.equals(dietCount.getDiet())).findFirst()
                .orElseGet(() -> { return new DietCount(Diet.OMNIVORE,0L); }).getCount();
        return Covers.coversFor(participants, numberOfMeal).countOf(omnivore);

    }

    @Nested
    @DisplayName("Get count of covers By Diet For Participants Should")
    class CountOfCoversByDietFor {

        @Test
        void return_list_one_cover_vegetarian_when_only_one_vegetarian_participant() {
            Participant participantVege = new Participant(Diet.VEGETARIAN);
            List<Participant> participants = Collections.singletonList(participantVege);
            Covers covers = new Covers();
            DietCount dietCount = new DietCount(Diet.VEGETARIAN,1L);
            assertThat(covers.countOfCoversByDietFor(participants)).contains(dietCount);
        }

        @Test
        void return_list_one_cover_omnivore_when_only_one_omnivore_participant() {
            Participant participantOmnivore = new Participant(Diet.OMNIVORE);
            List<Participant> participants = Collections.singletonList(participantOmnivore);
            Covers covers = new Covers();
            DietCount dietCount = new DietCount(Diet.OMNIVORE,1L);
            assertThat(covers.countOfCoversByDietFor(participants)).contains(dietCount);
        }

        @Test
        void return_list_two_cover_omnivore_when_two_omnivore_participants() {
            Participant participantOmnivore = new Participant(Diet.OMNIVORE);
            List<Participant> participants = Arrays.asList(participantOmnivore,participantOmnivore);
            Covers covers = new Covers();
            DietCount dietCount = new DietCount(Diet.OMNIVORE,2L);
            assertThat(covers.countOfCoversByDietFor(participants)).contains(dietCount);
        }

    }

}
