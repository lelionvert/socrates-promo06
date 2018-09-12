package fr.lacombe.magic6tem.price;

import fr.lacombe.magic6tem.conference.Participant;

class AccountingManager {

    public static Price calculatePrice(Attendee attendee) {
        if (attendee.choose(new DoubleRoomPackage())){
            return new Price(510);
        }
        return new Price(610);
    }
}
