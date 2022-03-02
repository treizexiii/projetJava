package engine;

public class Player extends Element {

    public Directions Direction;
    public PlayerTag Name;

    public Player(int x, int y, PlayerTag name) {
        super(x, y);
        this.Name = name;
    }

    public Directions getDirection() {
        return Direction;
    }

    public void move(int x, int y) {
        this.X += x;
        this.Y += y;
    }
}
