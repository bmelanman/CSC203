public class Team {
    private String name;
    private int size;

    public Team(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public boolean equals(Object team) {
        if (team == null) {
            return false;
        }
        if (team.getClass() != this.getClass()) {
            return false;
        }

        Team t = (Team) team;

        return (name.equals(t.name)) && (size == t.size);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + size;

        return result;
    }
}