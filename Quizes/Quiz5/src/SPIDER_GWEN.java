public class SPIDER_GWEN extends SuperHero{
    public SPIDER_GWEN(int strength, int superPower, boolean befriendsGalactus) {
        super(strength, superPower, befriendsGalactus);
    }

    @Override
    public void useSuperPower() {
        /* Assume code is unique and edits / accesses superPower variable */
        setSuperPower(getSuperPower());
    }
}
