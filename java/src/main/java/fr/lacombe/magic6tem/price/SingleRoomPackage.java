package fr.lacombe.magic6tem.price;

public class SingleRoomPackage implements PackageChoice {

    @Override
    public boolean is(PackageChoice packageChoice) {
        return packageChoice instanceof SingleRoomPackage;
    }
}
