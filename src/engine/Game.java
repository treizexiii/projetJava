package engine;

import java.util.List;
import java.util.Random;

import engine.Dtos.OrderDto;
import engine.interfaces.IPlayers;

public class Game {
    private WorldMap world;
    private IPlayers Players;
    private int Turns;
    private boolean Won;

    /***
     * Create a new game
     * 
     * @param width  Width of your world
     * @param heigth Heigth of your world
     * @param map    A hashmap of the world with 0 and 1 to draw element
     * @return A fresh new game
     */
    public static Game startGame(int width, int heigth, int[][] map) {
        Game newGame = new Game();
        newGame.world = WorldMap.buildWorld(width, heigth, map);
        Random rand = new Random();
        newGame.Players = new Players();
        for (PlayerTag tag : PlayerTag.values()) {
            int x, y;
            do {
                x = rand.nextInt(newGame.world.getWidth());
                y = rand.nextInt(newGame.world.getWidth());
            } while (newGame.world.getElement(x, y).Type != StaticElementType.EMPTY
                    && newGame.Players.get(x, y) != null);
            newGame.Players.add(new Player(x, y, tag));
        }
        return newGame;
    }

    /**
     * Play a new turn
     * 
     * @param request A list of move orders
     */
    public void playTurn(List<OrderDto> request) {
        for (OrderDto playerDto : request) {
            Player player = Players.get(playerDto.ref);
            Players.remove(player);
            this.movePlayer(player, Directions.valueOf(playerDto.moveTo.toUpperCase()));
            Players.add(player);
        }
        this.checkIsWinning();
        this.Turns++;
    }

    /**
     * Draw the world
     * 
     * @return a Table in HTML format
     */
    public String drawTable() {
        String table = "";
        table += "<table class=\"city\">";
        for (int i = 0; i < this.world.getWidth(); i++) {
            table += "<tr>";
            for (int j = 0; j < this.world.getHeight(); j++) {
                table += "<td>";
                Player player = Players.get(i, j);
                if (player != null) {
                    switch (player.Name) {
                        case A:
                            table += "<div class=\"empty\">A</div>";
                            break;
                        case B:
                            table += "<div class=\"empty\">B</div>";
                            break;
                        case C:
                            table += "<div class=\"empty\">C</div>";
                            break;
                        case D:
                            table += "<div class=\"empty\">D</div>";
                            break;
                        case X:
                            table += "<div class=\"empty\">X</div>";
                            break;
                    }
                } else {
                    StaticElement element = this.world.getElement(i, j);
                    switch (element.Type) {
                        case BUILDING:
                            table += "<div class=\"building\"></div>";
                            break;
                        case EMPTY:
                            table += "<div class=\"empty\"></div>";
                            break;
                    }
                }
                table += "</td>";
            }
            table += "</tr>";
        }
        table += "</table>";
        return table;

    }

    /**
     * Check the winning condition
     * 
     * @return true or false
     */
    public boolean getWon() {
        return this.Won;
    }

    /**
     * Return the current turn of the game
     * 
     * @return count of turns
     */
    public int getTurn() {
        return this.Turns;
    }

    private void movePlayer(Player player, Directions moveOrder) {
        try {
            StaticElement element = this.world.getElement(player.X, player.Y, moveOrder);
            if (element.Type == StaticElementType.EMPTY) {
                if (Players.get(element.X, element.Y) == null) {
                    player.X = element.X;
                    player.Y = element.Y;
                }
            }
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    private void checkIsWinning() {
        Player misterX = Players.get(PlayerTag.X);
        int count = 0;
        for (Directions direction : Directions.values()) {
            try {
                StaticElement element = this.world.getElement(misterX.X, misterX.Y, direction);
                if (element.Type == StaticElementType.EMPTY) {
                    if (Players.get(element.X, element.Y) != null) {
                        count++;
                    }
                }
            } catch (IllegalArgumentException e) {
                count++;
                continue;
            }
        }
        if (count >= 4) {
            this.Won = true;
        }
    }
}
