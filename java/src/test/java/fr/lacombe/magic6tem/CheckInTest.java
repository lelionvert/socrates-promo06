package fr.lacombe.magic6tem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CheckInTest {

    private LocalDateTime dateTime;
    private CheckIn checkIn;

    @BeforeEach
    void setUp() {
        dateTime = LocalDateTime.of(2018, 10, 25, 21, 0);
        checkIn = CheckIn.of(dateTime);
    }

    @Nested
    @DisplayName("isAfter should")
    class isAfter{

        @Test
        void return_true_when_checkIn_date_is_after_limit_date_time() {
            assertThat(checkIn.isAfter(dateTime.minusMinutes(1))).isTrue();
        }

        @Test
        void throw_exception_given_null_to_isAfter_() {
            assertThatThrownBy(() -> checkIn.isAfter(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("CheckIn Factory Should")
    class FactoryOfCheckIn{

        @Test
        void throw_exception_if_checkin_date_is_after_arrival_day(){
            assertThatThrownBy(() -> {
                LocalDateTime localDateTime = LocalDateTime.of(ConferenceCalendar.ARRIVAL_DAY.date,LocalTime.MIDNIGHT);
                CheckIn.of(localDateTime.plusDays(1));
            }).isInstanceOf(IllegalArgumentException.class);
        }
    }



}