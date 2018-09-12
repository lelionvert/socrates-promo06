package fr.lacombe.magic6tem.price;

import fr.lacombe.magic6tem.conference.Participant;

class AccountingManager {

    public static final DoubleRoomPackage DOUBLE_ROOM_PACKAGE = new DoubleRoomPackage();
    public static final TripleRoomPackage TRIPLE_ROOM_PACKAGE = new TripleRoomPackage();
    public static final NoRoomPackage NO_ROOM_PACKAGE = new NoRoomPackage();

    public static Price calculatePrice(Attendee attendee) {
        if (attendee.choose(DOUBLE_ROOM_PACKAGE)){
            return new Price(510);
        }
        if (attendee.choose(TRIPLE_ROOM_PACKAGE)){
            return new Price(410);
        }
        if (attendee.choose(NO_ROOM_PACKAGE)){
            return new Price(240);
        }
        return new Price(610);
    }
}
