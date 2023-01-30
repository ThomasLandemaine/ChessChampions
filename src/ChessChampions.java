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
                return other.age - this.age;
            }
            return this.elo - other.elo;
        }
    }

    public static List<Player> findChampions(List<Player> players) {
        List<Player> champions = new ArrayList<>();
        Collections.sort(players);
        Player bestPlayer = new Player("", 0, 0);
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            boolean isChampion = !isStrictlyOlderAndWeaker(bestPlayer, player);
            for (int j = i + 1; j < players.size(); j++) {
                Player other = players.get(j);
                if (!isChampion  || isOlderAndWeaker(player, other)) {
                    if (!hasSameAgeAndElo(player, other)) {
                        isChampion = false;
                    }
                    bestPlayer = setBestPlayer(other);
                    break;
                }
            }
            if (isChampion) {
                champions.add(player);
            }
        }
        return champions;
    }

    private static Player setBestPlayer(Player newBestPlayer) {
        return new Player("BestPlayer", newBestPlayer.age, newBestPlayer.elo);
    }

    private static boolean hasSameAgeAndElo(Player player, Player other) {
        return player.age == other.age && player.elo == other.elo;
    }

    private static boolean isOlderAndWeaker(Player player, Player other) {
        return player.age >= other.age && player.elo <= other.elo;
    }

    private static boolean isStrictlyOlderAndWeaker(Player bestPlayer, Player player) {
        return player.age > bestPlayer.age && player.elo < bestPlayer.elo;
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
