package fr.lacombe.magic6tem.price;

class NoRoomPackage implements PackageChoice {

    @Override
    public boolean is(PackageChoice packageChoice) {
        return packageChoice instanceof NoRoomPackage;
    }
}
