package fr.lacombe.magic6tem.price;

class Attendee {
    private PackageChoice packageChoice;

    public Attendee(PackageChoice packageChoice) {
        this.packageChoice = packageChoice;
    }

    public boolean choose(PackageChoice packageChoice) {
        return  this.packageChoice instanceof DoubleRoomPackage;
    }
}
