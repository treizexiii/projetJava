package engine;

import java.util.NoSuchElementException;

import engine.interfaces.IWorldIterator;

public class WorldIterator implements IWorldIterator {

    private WorldMap map;
    private int x;
    private int y;

    public WorldIterator(WorldMap map) {
        super();
        this.map = map;
        this.x = -1;
        this.y = 0;
    }

    @Override
    public boolean hasNext() {
        if (this.x + 1 < map.getWidth())
            return true;
        if (this.y + 1 < map.getHeight())
            return true;
        return false;
    }

    @Override
    public StaticElement next() {
        if (this.x + 1 < map.getWidth()) {
            this.x++;
        } else if (this.y + 1 < map.getHeight()) {
            this.x = 0;
            this.y++;
        } else {
            throw new NoSuchElementException("Pas d'element");
        }
        return map.getElement(this.x, this.y);
    }

}
