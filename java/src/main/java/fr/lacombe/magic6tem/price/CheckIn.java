package fr.lacombe.magic6tem.price;

import java.time.LocalDateTime;

class CheckIn {

    private LocalDateTime dateTime;

    public CheckIn(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isAfter(LocalDateTime dateTime) {
        return this.dateTime.isAfter(dateTime);
    }
}
