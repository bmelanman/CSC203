public class IRON_HEART extends SuperHero implements Fight {
    public IRON_HEART(int strength, int superPower, boolean befriendsGalactus) {
        super(strength, superPower, befriendsGalactus);
    }

    @Override
    public void useSuperPower() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fight() {
        decrementStrengthByOne();
        useSuit();
    }

    private void useSuit() {
        /* assume unique code which only uses / accesses the strength variable */
        setStrength(getStrength());
    }

}
