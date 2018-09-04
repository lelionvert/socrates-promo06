package fr.lacombe.magic6tem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class DietTest {

    public static final Participant OMNIVORE_PARTICIPANT = new Participant(Diet.OMNIVORE);

    @Nested
    @DisplayName("Get count of covers should")
    class GetCountCover {

        @Test
        public void return_0_when_given_no_participant(){
            List<Participant> participants = new ArrayList<>();
            assertThat(Restaurant.getCountOfCovers(participants, 1)).isEqualTo(0);
        }

        @Test
        void return_1_given_one_participant() {
            List<Participant> participants = new ArrayList<>();
            participants.add(new Participant(Diet.OMNIVORE));
            assertThat(Restaurant.getCountOfCovers(participants, 1)).isEqualTo(1);
        }

        @Test
        void return_2_given_2_participant() {
            List<Participant> participants = new ArrayList<>();
            participants.add(new Participant(Diet.OMNIVORE));
            participants.add(new Participant(Diet.OMNIVORE));
            assertThat(Restaurant.getCountOfCovers(participants, 1)).isEqualTo(2);
        }

        @Test
        void return_0_given_1_participants_and_0_meal() {
            List<Participant> participants = new ArrayList<>();
            participants.add(new Participant(Diet.OMNIVORE));
            assertThat(Restaurant.getCountOfCovers(participants, 0)).isEqualTo(0);
        }

        @Test
        void return_1_given_one_participant_and_1_meal() {
            List<Participant> participants = new ArrayList<>();
            participants.add(new Participant(Diet.OMNIVORE));
            assertThat(Restaurant.getCountOfCovers(participants,1)).isEqualTo(1);
        }

        @Test
        void return_6_given_one_participant_and_6_meals() {
            List<Participant> participants = new ArrayList<>();
            participants.add(new Participant(Diet.OMNIVORE));
            assertThat(Restaurant.getCountOfCovers(participants,6)).isEqualTo(6);
        }

        @Test
        void return_covers_by_diet_for_vegan_and_omnivore() {
            List<Participant> participants = new ArrayList<>();
            participants.add(OMNIVORE_PARTICIPANT);
            participants.add(new Participant(Diet.VEGAN));

            Map<Diet,Integer> covers = new HashMap<>();
            covers.put(Diet.OMNIVORE,6);
            covers.put(Diet.VEGAN,6);

            assertThat(Restaurant.getCountOfCoversBis(participants, 6)).isEqualTo(covers);
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
            participants.add(new Participant(Diet.PESCARIAN));
            participants.add(new Participant(Diet.PESCARIAN));
            participants.add(new Participant(Diet.PESCARIAN));
            participants.add(new Participant(Diet.PESCARIAN));

            Map<Diet,Integer> covers = new HashMap<>();
            covers.put(Diet.OMNIVORE,6);
            covers.put(Diet.VEGAN,12);
            covers.put(Diet.VEGETARIAN,18);
            covers.put(Diet.PESCARIAN,24);

            assertThat(Restaurant.getCountOfCoversBis(participants, 6)).isEqualTo(covers);
        }
    }


}
