package engine;

import java.util.ArrayList;
import java.util.List;

import engine.interfaces.IPlayers;

public class Players implements IPlayers {
    private List<Player> Players;

    public Players() {
        this.Players = new ArrayList<Player>();
    }

    public Player get(int x, int y) {
        for (Player player : this.Players) {
            if (player.X == x && player.Y == y) {
                return player;
            }
        }
        return null;
    }

    public Player get(String reference) {
        for (Player player : this.Players) {
            if (player.Name == PlayerTag.valueOf(reference.toUpperCase())) {
                return player;
            }
        }
        return null;
    }

    @Override
    public Player get(PlayerTag reference) {
        for (Player player : this.Players) {
            if (player.Name == reference) {
                return player;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return Players.size();
    }

    @Override
    public void add(Player element) {
        Players.add(element);

    }

    @Override
    public Player get(int i) {
        return Players.get(i);
    }

    @Override
    public void remove(Player player) {
        Players.remove(player);
    }
}
