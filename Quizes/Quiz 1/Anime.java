
public class Anime {

    private int minutes;
    private String name;
    private Boolean onCrunchyRoll;

    public Anime(int minutes, String name, Boolean onCrunchyRoll){
        this.minutes = minutes;
        this.name = name;
        this.onCrunchyRoll = onCrunchyRoll;
    }

    public Boolean getOnCrunchyRoll() {
        return this.onCrunchyRoll;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setOnCrunchyRoll(Boolean onCrunchyRoll) {
        this.onCrunchyRoll = onCrunchyRoll;
    }

    public boolean longerThanGiven(int threshold) {
        return (this.minutes > threshold);
    }

    public void checkName(){
        System.out.println(name);
    }
}
