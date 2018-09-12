package fr.lacombe.magic6tem.price;

import java.time.LocalDateTime;

class CheckOut {

    private LocalDateTime dateTime;

    public CheckOut(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isBefore(LocalDateTime dateTime) {
        return this.dateTime.isBefore(dateTime);
    }
}
