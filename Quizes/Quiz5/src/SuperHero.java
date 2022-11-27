// -------------SuperHero.java----------------------------

public abstract class SuperHero {
    private int strength;
    private int superPower;
    private final boolean befriendsGalactus;

    public SuperHero(int strength, int superPower, boolean befriendsGalactus) {
        this.strength = strength;
        this.superPower = superPower;
        this.befriendsGalactus = befriendsGalactus;
    }


    public int getSuperPower() {
        return superPower;
    }

    public void setSuperPower(int superPower){
        this.superPower = superPower;
    }

    public int getStrength(){
        return this.strength;
    }
    public void setStrength(int strength){
        this.strength = strength;
    }

    public boolean getBefriendsGalactus(){
        return this.befriendsGalactus;
    }

    public abstract void useSuperPower();

    void decrementStrengthByOne(){
        strength--;
    }
}
