import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChessChampions {
    static class Player implements Comparable<Player> {
        String name;
        int age;
        int elo;

        public Player(String name, int age, int elo) {
            this.name = name;
            this.age = age;
            this.elo = elo;
        }

        @Override
        public int compareTo(Player other) {
            if (this.age != other.age) {
                return this.age - other.age;
            }
            return other.elo - this.elo;
        }
    }

    public static List<Player> findChampions(List<Player> players) {
        List<Player> champions = new ArrayList<>();
        Collections.sort(players);
        champions.add(players.get(0));
        for (int i = 1; i < players.size(); i++) {
            Player champion = champions.get(champions.size() - 1);
            Player player = players.get(i);
            if (isBetterThanTheChampion(champion, player)) {
                champions.add(player);
            }
        }
        return champions;
    }

    private static boolean isBetterThanTheChampion(Player champion, Player player) {
        return ((player.elo > champion.elo) || (player.age == champion.age && player.elo == champion.elo));
    }

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        players.add(new Player("player1", 30, 2800));
        players.add(new Player("player2", 25, 2700));
        players.add(new Player("player3", 32, 2900));
        players.add(new Player("player4", 28, 2600));
        players.add(new Player("player5", 17, 2700));
        players.add(new Player("player6", 17, 2700));
        players.add(new Player("player7", 16, 1000));
        players.add(new Player("player8", 16, 1200));
        players.add(new Player("player9", 46, 3000));
        players.add(new Player("player10", 47, 3400));
        players.add(new Player("player11", 46, 1));

        List<Player> champions = findChampions(players);
        for (Player champion : champions) {
            System.out.println(champion.name + " " + champion.age + " " + champion.elo);
        }
    }
}
