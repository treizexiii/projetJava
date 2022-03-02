package engine;

import engine.interfaces.IWorldIterator;

public class WorldMap {
    private int width;
    private int height;
    private StaticElement[][] Elements;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public StaticElement getElement(int x, int y) {
        return this.Elements[x][y];
    }

    public StaticElement getElement(int x, int y, Directions d) {
        switch (d) {
            case NONE:
                if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
                    throw new IllegalArgumentException("Coordonnées invalides");
                }
                return this.Elements[x][y];
            case NORTH:
                return this.getElement(x, y - 1, Directions.NONE);
            case SOUTH:
                return this.getElement(x, y + 1, Directions.NONE);
            case EAST:
                return this.getElement(x + 1, y, Directions.NONE);
            case WEST:
                return this.getElement(x - 1, y - 1, Directions.NONE);
            default:
                throw new IllegalArgumentException("Coordonnées invalides");
        }
    }

    public static WorldMap buildWorld(int width, int heigth, int[][] map) {
        WorldMap world = new WorldMap();
        world.width = width;
        world.height = heigth;
        world.Elements = new StaticElement[width][heigth];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {
                    world.Elements[i][j] = new StaticElement(i, j, StaticElementType.BUILDING);
                } else {
                    world.Elements[i][j] = new StaticElement(i, j, StaticElementType.EMPTY);
                }
            }
        }
        return world;
    }

    public IWorldIterator iterator() {
        return new WorldIterator(this);
    }
}
