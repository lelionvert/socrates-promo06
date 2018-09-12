package fr.lacombe.magic6tem.price;

import fr.lacombe.magic6tem.conference.Participant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

class PriceCalculatorTest {

    @Test
    void return_610_when_participant_is_in_time() {
        Attendee attendee = new Attendee(new SingleRoomPackage());

        Assertions.assertThat(AccountingManager.calculatePrice(attendee)).isEqualTo(new Price(610));
    }
    @Test
    void return_510_when_participant_is_in_time_and_double_room() {
        PackageChoice doubleChoice = new DoubleRoomPackage();
        Attendee attendee = new Attendee(doubleChoice);
        assertThat(AccountingManager.calculatePrice(attendee)).isEqualTo(new Price(510));
    }


    @Test
    void return_410_when_participant_is_in_time_and_triple_room() {
        PackageChoice tripleRoomPackage = new TripleRoomPackage();
        Attendee attendee = new Attendee(tripleRoomPackage);
        assertThat(AccountingManager.calculatePrice(attendee)).isEqualTo(new Price(410));
    }
    @Test
    void return_2400_when_participant_is_in_time_and_no_room() {
        PackageChoice noRoomPackage = new NoRoomPackage();
        Attendee attendee = new Attendee(noRoomPackage);
        assertThat(AccountingManager.calculatePrice(attendee)).isEqualTo(new Price(240));
    }
}
