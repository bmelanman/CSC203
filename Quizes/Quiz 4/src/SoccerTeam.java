import java.util.Objects;

public class SoccerTeam extends Team {
    private int totalGoals;

    public SoccerTeam(String name, int size, int totalGoals) {
        super(name, size);
        this.totalGoals = totalGoals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.totalGoals);
    }

    @Override
    public boolean equals(Object team){
        return (((Team) team).equals(this)) && (totalGoals == ((SoccerTeam) team).totalGoals);
    }
}