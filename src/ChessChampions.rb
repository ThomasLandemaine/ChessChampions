class Player
  attr_accessor :name, :age, :elo
  def initialize(name, age, elo)
    @name = name
    @age = age
    @elo = elo
  end

  def <=>(other)
    if self.age != other.age
      return other.age <=> self.age
    end
    self.elo <=> other.elo
  end
end

def find_champions(players)
  champions = []
  players.sort!
  best_player = Player.new("", 0, 0)
  players.each_with_index do |player, i|
    is_champion = !is_strictly_older_and_weaker(best_player, player)
    (i + 1).upto(players.size - 1) do |j|
      other = players[j]
      if !is_champion || is_older_and_weaker(player, other)
        unless has_same_age_and_elo(player, other)
          is_champion = false
        end
        best_player = set_best_player(other)
        break
      end
    end
    champions << player if is_champion
  end
  champions
end

def set_best_player(new_best_player)
  Player.new("BestPlayer", new_best_player.age, new_best_player.elo)
end

def has_same_age_and_elo(player, other)
  player.age == other.age && player.elo == other.elo
end

def is_older_and_weaker(player, other)
  player.age >= other.age && player.elo <= other.elo
end

def is_strictly_older_and_weaker(best_player, player)
  player.age > best_player.age && player.elo < best_player.elo
end

players = []
players << Player.new("player1", 30, 2800)
players << Player.new("player2", 25, 2700)
players << Player.new("player3", 32, 2900)
players << Player.new("player4", 28, 2600)
players << Player.new("player5", 17, 2700)
players << Player.new("player6", 17, 2700)
players << Player.new("player7", 16, 1000)
players << Player.new("player8", 16, 1200)
players << Player.new("player9", 46, 3000)
players << Player.new("player10", 47, 3400)
players << Player.new("player11", 46, 1)

champions = find_champions(players)
champions.each do |champion|
  puts "#{champion.name} #{champion.age} #{champion.elo}"
end
