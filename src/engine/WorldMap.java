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
        return this.Elements[y][x];
    }

    public StaticElement getElement(int x, int y, Directions d) {
        switch (d) {
            case NONE:
                if (x < 0 || x > this.width || y < 0 || y > this.height) {
                    throw new IllegalArgumentException("Coordonnées invalides");
                }
                return this.Elements[y][x];
            case NORTH:
                return this.getElement(x, y - 1, Directions.NONE);
            case SOUTH:
                return this.getElement(x, y + 1, Directions.NONE);
            case EAST:
                return this.getElement(x + 1, y, Directions.NONE);
            case WEST:
                return this.getElement(x - 1, y, Directions.NONE);
            default:
                throw new IllegalArgumentException("Coordonnées invalides");
        }
    }

    public static WorldMap buildWorld(int width, int heigth, int[][] map) {
        WorldMap world = new WorldMap();
        world.width = width;
        world.height = heigth;
        world.Elements = new StaticElement[heigth][width];

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map.length; x++) {
                if (map[y][x] == 1) {
                    world.Elements[y][x] = new StaticElement(x, y, StaticElementType.BUILDING);
                } else {
                    world.Elements[y][x] = new StaticElement(x, y, StaticElementType.EMPTY);
                }
            }
        }

        // for (int x = 0; x < map.length; x++) {
        // for (int y = 0; y < map[x].length; y++) {
        // if (map[x][y] == 1) {
        // world.Elements[x][y] = new StaticElement(x, y, StaticElementType.BUILDING);
        // } else {
        // world.Elements[x][y] = new StaticElement(x, y, StaticElementType.EMPTY);
        // }
        // }
        // }

        // for (int i = 0; i < map.length; i++) {
        // for (int j = 0; j < map[i].length; j++) {
        // if (map[j][i] == 1) {
        // world.Elements[j][i] = new StaticElement(j, i, StaticElementType.BUILDING);
        // } else {
        // world.Elements[j][i] = new StaticElement(j, i, StaticElementType.EMPTY);
        // }
        // }
        // }
        return world;
    }

    public IWorldIterator iterator() {
        return new WorldIterator(this);
    }
}
