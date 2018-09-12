package fr.lacombe.magic6tem.price;

class TripleRoomPackage implements PackageChoice {

    @Override
    public boolean is(PackageChoice packageChoice) {
        return packageChoice instanceof TripleRoomPackage;
    }
}
