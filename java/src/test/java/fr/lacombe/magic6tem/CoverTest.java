package fr.lacombe.magic6tem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CoverTest {

    public static final Participant OMNIVORE_PARTICIPANT = new Participant(Diet.OMNIVORE);
    public static final Participant VEGAN_PARTICIPANT = new Participant(Diet.VEGAN);
    public static final Restaurant RESTAURANT = new Restaurant();

    @Nested
    @DisplayName("Get count of covers should")
    class GetCountCover {

        @Test
        public void return_0_when_given_no_participant(){
            List<Participant> participants = new ArrayList<>();
            assertThat(((int) new Restaurant().coversFor(participants, 1).countOf(Diet.OMNIVORE))).isEqualTo(0);
        }

        @Test
        void return_1_given_one_participant() {
            List<Participant> participants = new ArrayList<>();
            participants.add(new Participant(Diet.OMNIVORE));
            assertThat((int) new Restaurant().coversFor(participants, 1).countOf(Diet.OMNIVORE)).isEqualTo(1);
        }

        @Test
        void return_2_given_2_participant() {
            List<Participant> participants = new ArrayList<>();
            participants.add(new Participant(Diet.OMNIVORE));
            participants.add(new Participant(Diet.OMNIVORE));
            assertThat((int) new Restaurant().coversFor(participants, 1).countOf(Diet.OMNIVORE)).isEqualTo(2);
        }

        @Test
        void return_0_given_1_participants_and_0_meal() {
            List<Participant> participants = new ArrayList<>();
            participants.add(new Participant(Diet.OMNIVORE));
            assertThat((int) new Restaurant().coversFor(participants, 0).countOf(Diet.OMNIVORE)).isEqualTo(0);
        }

        @Test
        void return_1_given_one_participant_and_1_meal() {
            List<Participant> participants = new ArrayList<>();
            participants.add(new Participant(Diet.OMNIVORE));
            assertThat((int) new Restaurant().coversFor(participants, 1).countOf(Diet.OMNIVORE)).isEqualTo(1);
        }

        @Test
        void return_6_given_one_participant_and_6_meals() {
            List<Participant> participants = new ArrayList<>();
            participants.add(new Participant(Diet.OMNIVORE));
            assertThat((int) RESTAURANT.coversFor(participants, 6).countOf(Diet.OMNIVORE)).isEqualTo(6);
        }

        @Test
        void return_covers_by_diet_for_vegan_and_omnivore() {
            List<Participant> participants = new ArrayList<>();
            participants.add(OMNIVORE_PARTICIPANT);
            participants.add(VEGAN_PARTICIPANT);

            Covers covers = new Restaurant().coversFor(participants, 6);

            assertThat(covers.countOf(Diet.OMNIVORE)).isEqualTo(6);
            assertThat(covers.countOf(Diet.VEGAN)).isEqualTo(6);
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

            Covers covers = new Restaurant().coversFor(participants, 6);

            assertThat(covers.countOf(Diet.OMNIVORE)).isEqualTo(6);
            assertThat(covers.countOf(Diet.VEGAN)).isEqualTo(12);
            assertThat(covers.countOf(Diet.VEGETARIAN)).isEqualTo(18);
            assertThat(covers.countOf(Diet.PESCATARIAN)).isEqualTo(24);
        }

        @Test
        void return_five_covers_for_participant_who_is_late_for_6_meals() {

            List<Participant> participants = new ArrayList<>();
            CheckIn checkIn = CheckIn.of(LocalDateTime.of(2018,10,25,21,1));
            Participant lateOmnivoreParticipant = new Participant(Diet.OMNIVORE, checkIn);
            participants.add(lateOmnivoreParticipant);


            Integer countOfCover = RESTAURANT.coversFor(participants, 6).countOf(Diet.OMNIVORE);

            assertThat(countOfCover).isEqualTo(5);
        }

        @Test
        void return_five_covers_for_participants_which_one_is_late_and_omni_other_vegan_and_on_time_for_6_meals() {

            List<Participant> participants = new ArrayList<>();
            CheckIn checkIn = CheckIn.of(LocalDateTime.of(2018,10,25,21,1));
            Participant lateOmnivoreParticipant = new Participant(Diet.OMNIVORE, checkIn);
            Participant veganParticipant = new Participant(Diet.VEGAN);
            participants.add(lateOmnivoreParticipant);
            participants.add(veganParticipant);


            Integer countOfCoverOmnivore = RESTAURANT.coversFor(participants, 6).countOf(Diet.OMNIVORE);
            Integer countOfCoverVegan = RESTAURANT.coversFor(participants, 6).countOf(Diet.VEGAN);

            assertThat(countOfCoverOmnivore).isEqualTo(5);
            assertThat(countOfCoverVegan).isEqualTo(6);
        }
    }

}
