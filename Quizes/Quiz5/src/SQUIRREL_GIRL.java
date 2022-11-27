public class SQUIRREL_GIRL extends SuperHero implements Fight{
    public SQUIRREL_GIRL(int strength, int superPower, boolean befriendsGalactus) {
        super(strength, superPower, befriendsGalactus);
    }

    @Override
    public void useSuperPower() {
        /* Assume code is unique and edits / accesses superPower variable */
        setSuperPower(getSuperPower());
    }

    @Override
    public void fight() {
        decrementStrengthByOne();
        fightGalactus();
    }

    private void fightGalactus() {
        /* assume unique code which only uses befriendsGalactus variable */
        System.out.println(getBefriendsGalactus());
    }

}
