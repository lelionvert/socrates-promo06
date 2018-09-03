package fr.lacombe.magic6tem;

import java.time.LocalDate;


public enum ConferenceCalendar {
    ARRIVAL_DAY(LocalDate.of(2018,10,25));

    public final LocalDate date;

    ConferenceCalendar(LocalDate date) {
        this.date = date;
    };
}
