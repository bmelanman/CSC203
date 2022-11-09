public class Indoor extends SoccerTeam {

    private int goalsOffWalls;

    public Indoor(String name, int size, int totalGoals, int goalsOffWalls) {
        super(name, size, totalGoals);
        this.goalsOffWalls = goalsOffWalls;
    }
}