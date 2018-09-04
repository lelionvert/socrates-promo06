package fr.lacombe.magic6tem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DietTest {

    @Nested
    @DisplayName("Get count of covers should")
    class GetCountCover {

        @Test
        public void return_0_when_given_no_participant(){
            List<Participant> participants = new ArrayList<>();
            assertThat(Restaurant.getCountOfCovers(participants)).isEqualTo(0);
        }

        @Test
        void return_1_given_one_participant() {
            List<Participant> participants = new ArrayList<>();
            participants.add(new Participant());
            assertThat(Restaurant.getCountOfCovers(participants)).isEqualTo(1);
        }

        @Test
        void return_2_given_2_participant() {
            List<Participant> participants = new ArrayList<>();
            participants.add(new Participant());
            participants.add(new Participant());
            assertThat(Restaurant.getCountOfCovers(participants)).isEqualTo(2);
        }


    }


}
