package fr.lacombe.magic6tem.price;

import java.time.LocalDateTime;

class Attendee {
    private PackageChoice packageChoice;
    private CheckIn checkIn;
    private CheckOut checkOut;

    public Attendee(PackageChoice packageChoice) {
        this.packageChoice = packageChoice;
        this.checkIn = new CheckIn(LocalDateTime.of(2018,10,25, 18,0));
        this.checkOut = new CheckOut(LocalDateTime.of(2018,10,28, 14,0));

    }

    public Attendee(PackageChoice packageChoice, CheckIn checkIn, CheckOut checkOut) {
        this.packageChoice = packageChoice;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public boolean choose(PackageChoice packageChoice) {
        return  this.packageChoice.is(packageChoice);
    }

    public boolean checkinAfter(LocalDateTime dateTime) {
        return this.checkIn.isAfter(dateTime);
    }

    public boolean checkoutBefore(LocalDateTime dateTime) {
        return this.checkOut.isBefore(dateTime);
    }
}
