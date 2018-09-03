package fr.lacombe.magic6tem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CheckInTest {
    LocalDateTime dateTime;
    CheckIn checkIn;
    @BeforeEach
    void setUp() {
        dateTime = LocalDateTime.of(2018, 10, 25, 21, 00);
        checkIn = CheckIn.of(dateTime);
    }

    @Test
    void isAfter() {
        assertThat(checkIn.isAfter(dateTime.minusMinutes(1))).isTrue();
    }

    @Test
    void send_null_to_isAfter_should_throw_exception() {
        assertThatThrownBy(() -> checkIn.isAfter(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void cannot_create_checkin_before_arrival_day(){
        assertThatThrownBy(() -> {
            LocalDateTime localDateTime = LocalDateTime.of(ConferenceCalendar.ARRIVAL_DAY.date,LocalTime.MIDNIGHT);
            CheckIn.of(localDateTime.plusDays(1));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}