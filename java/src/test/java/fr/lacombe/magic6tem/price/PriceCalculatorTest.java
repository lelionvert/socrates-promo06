package fr.lacombe.magic6tem.price;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PriceCalculatorTest {

    AccountingManager accountingManager;


    @BeforeEach
    public void setUp() {

        Map<PackageChoice, Price> packageChoicePriceMap = new HashMap<>();
        DoubleRoomPackage DOUBLE_ROOM_PACKAGE = new DoubleRoomPackage();
        TripleRoomPackage TRIPLE_ROOM_PACKAGE = new TripleRoomPackage();
        NoRoomPackage NO_ROOM_PACKAGE = new NoRoomPackage();
        SingleRoomPackage SINGLE_ROOM_PACKAGE  = new SingleRoomPackage();

        Price DOUBLE_ROOM_PRICE = new Price(510);
        Price TRIPLE_ROOM_PRICE = new Price(410);
        Price NO_ROOM_PRICE = new Price(240);
        Price SINGLE_ROOM_PRICE = new Price(610);

        packageChoicePriceMap.put(NO_ROOM_PACKAGE, NO_ROOM_PRICE);
        packageChoicePriceMap.put(DOUBLE_ROOM_PACKAGE, DOUBLE_ROOM_PRICE);
        packageChoicePriceMap.put(TRIPLE_ROOM_PACKAGE, TRIPLE_ROOM_PRICE);
        packageChoicePriceMap.put(SINGLE_ROOM_PACKAGE, SINGLE_ROOM_PRICE);

        this.accountingManager = new AccountingManager(packageChoicePriceMap, LocalDateTime.of(2018, 10, 25, 21, 0), LocalDateTime.of(2018, 10, 28, 12, 0), 40);
    }

    @Nested
    class fullMealEventTest {
        @Test
        void return_610_when_participant_is_in_time() {
            Attendee attendee = new Attendee(new SingleRoomPackage());

            Assertions.assertThat(accountingManager.calculatePrice(attendee)).isEqualTo(new Price(610));
        }

        @Test
        void return_510_when_participant_is_in_time_and_double_room() {
            PackageChoice doubleChoice = new DoubleRoomPackage();
            Attendee attendee = new Attendee(doubleChoice);
            assertThat(accountingManager.calculatePrice(attendee)).isEqualTo(new Price(510));
        }

        @Test
        void return_410_when_participant_is_in_time_and_triple_room() {
            PackageChoice tripleRoomPackage = new TripleRoomPackage();
            Attendee attendee = new Attendee(tripleRoomPackage);
            assertThat(accountingManager.calculatePrice(attendee)).isEqualTo(new Price(410));
        }

        @Test
        void return_240_when_participant_is_in_time_and_no_room() {
            PackageChoice noRoomPackage = new NoRoomPackage();
            Attendee attendee = new Attendee(noRoomPackage);
            assertThat(accountingManager.calculatePrice(attendee)).isEqualTo(new Price(240));
        }

    }

    @Test
    @DisplayName(" One participant that arrive late ")
    void acceptance_test_case_1() {
        Attendee attendee = new Attendee(new SingleRoomPackage(),
                new CheckIn(LocalDateTime.of(2018, 10, 26, 1, 0)),
                new CheckOut(LocalDateTime.of(2018, 10, 28, 14, 0)));

        Assertions.assertThat(accountingManager.calculatePrice(attendee)).isEqualTo(new Price(610 - 40));
    }

    @Test
    void acceptance_test_case_2() {
        Attendee attendee = new Attendee(new SingleRoomPackage(),
                new CheckIn(LocalDateTime.of(2018, 10, 25, 18, 0)),
                new CheckOut(LocalDateTime.of(2018, 10, 27, 18, 0)));

        Assertions.assertThat(accountingManager.calculatePrice(attendee)).isEqualTo(new Price(610 - 40));
    }


    @Test
    void return_570_when_participant_checkins_late_for_first_meal() {
        Attendee attendee = new Attendee(new SingleRoomPackage(),
                new CheckIn(LocalDateTime.of(2018, 10, 25, 21, 1)),
                new CheckOut(LocalDateTime.of(2018, 10, 28, 14, 0)));

        Assertions.assertThat(accountingManager.calculatePrice(attendee)).isEqualTo(new Price(610 - 40));
    }

    @Test
    void return_570_when_participant_checkout_too_soon_for_last_meal() {
        Attendee attendee = new Attendee(new SingleRoomPackage(),
                new CheckIn(LocalDateTime.of(2018, 10, 25, 18, 0)),
                new CheckOut(LocalDateTime.of(2018, 10, 28, 11, 0)));

        Assertions.assertThat(accountingManager.calculatePrice(attendee)).isEqualTo(new Price(610 - 40));
    }

    @Test
    void return_470_when_participant_checkout_late_for_first_meal_and_choose_double() {
        Attendee attendee = new Attendee(new DoubleRoomPackage(),
                new CheckIn(LocalDateTime.of(2018, 10, 25, 21, 1)),
                new CheckOut(LocalDateTime.of(2018, 10, 28, 14, 0)));

        Assertions.assertThat(accountingManager.calculatePrice(attendee)).isEqualTo(new Price(510 - 40));
    }

    @Test
    void return_470_when_participant_checkout_too_soon_for_last_meal_and_double_room() {
        Attendee attendee = new Attendee(new DoubleRoomPackage(),
                new CheckIn(LocalDateTime.of(2018, 10, 25, 18, 0)),
                new CheckOut(LocalDateTime.of(2018, 10, 28, 11, 0)));

        Assertions.assertThat(accountingManager.calculatePrice(attendee)).isEqualTo(new Price(510 - 40));
    }

    @Test
    void return_price_minus_40_minus_40_when_participant_checkins_late_for_first_meakl_and_checkout_too_soon_for_last_meal() {
        Attendee attendee = new Attendee(new SingleRoomPackage(),
                new CheckIn(LocalDateTime.of(2018, 10, 25, 21, 1)),
                new CheckOut(LocalDateTime.of(2018, 10, 28, 11, 0)));

        Assertions.assertThat(accountingManager.calculatePrice(attendee)).isEqualTo(new Price(610 - 40 - 40));
    }
}
