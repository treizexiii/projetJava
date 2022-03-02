package engine.interfaces;

import engine.Player;
import engine.PlayerTag;

public interface IPlayers {
    int size();

    void add(Player player);

    Player get(int i);

    Player get(int x, int y);

    Player get(PlayerTag reference);

    Player get(String reference);

    void remove(Player player);
}
