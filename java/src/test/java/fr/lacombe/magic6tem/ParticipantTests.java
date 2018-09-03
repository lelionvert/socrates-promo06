package fr.lacombe.magic6tem;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

class IsArrivalTimeAfterShould {
    private Participant participant = new Participant(LocalTime.of(12,0));
    @Test
    void return_true_if_time_after_arrival_time() {
        assertThat(participant.isArrivalTimeAfter(LocalTime.of(9,0))).isTrue();
    }

    @Test
    void return_false_if_time_before_arrival_time() {
        assertThat(participant.isArrivalTimeAfter(LocalTime.of(18,0))).isFalse();

    }
}

class FactoryShould {
    @Test
    void return_new_participant_with_corresponding_arrivalTime() {
        assertThat(Participant.ofArrivalTime(12,00))
                .isEqualTo(new Participant(LocalTime.of(12,0)));
    }
}