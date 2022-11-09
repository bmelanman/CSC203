public class Main {
    public static void main(String[] args) {
        Team t1 = new Team("CoolTeam", 10);
        Team t2 = new SoccerTeam("CoolTeam", 10, 5);
        Team t3 = new Team("CoolTeam", 10);

        System.out.println(t1.equals((Team) t2));
        System.out.println(t2.equals(t1));
        System.out.println(t1.equals(t3));

    }
}