package fr.lacombe.magic6tem.price;

public class DoubleRoomPackage implements  PackageChoice {

    @Override
    public boolean is(PackageChoice packageChoice) {
        return packageChoice instanceof DoubleRoomPackage;
    }
}
