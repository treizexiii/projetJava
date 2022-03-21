package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import core.Application;
import engine.Directions;
import engine.Game;
import engine.Dtos.OrderDto;
import repositories.interfaces.IScoreRepository;

@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024
        * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class GameServlet extends HttpServlet {
    private IScoreRepository _scoreRepository;

    public GameServlet() {
        super();
        Application app = new Application();
        this._scoreRepository = (IScoreRepository) app.getRepository("scores");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("joueur") != null) {
            if (session.getAttribute("isRunning") == null) {
                session.setAttribute("isRunning", true);
            } else {
                session.removeAttribute("game");
                session.removeAttribute("isRunning");
            }
        }
        this.getServletContext().getRequestDispatcher("/index").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("joueur") != null) {
            if (session.getAttribute("game") == null) {
                // Create a new game
                Part gameMap = request.getPart("game-map");
                InputStream fileContent = gameMap.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent, "UTF8"));

                String firstLine = reader.readLine();
                int X = Integer.parseInt(firstLine.split(":")[1]);
                int Y = Integer.parseInt(firstLine.split(":")[2]);
                int[][] map = new int[Y][X];
                for (int y = 0; y < map.length; y++) {
                    String[] line = reader.readLine().split(":");
                    for (int x = 0; x < map[y].length; x++) {
                        map[y][x] = Integer.parseInt(line[x + 1]);
                    }
                }

                Game game = Game.startGame(X, Y, map);

                session.setAttribute("game", game);
                request.setAttribute("map", game.drawTable());
            } else {
                Game game = (Game) session.getAttribute("game");

                // CHeck the winning conditions
                if (game.getWon() == true) {
                    session.removeAttribute("game");
                    session.removeAttribute("isRunning");
                    this._scoreRepository.updateWinningScore((String) session.getAttribute("joueur"));
                    request.setAttribute("error", "You won");
                } else if (game.getTurn() >= 4) {
                    session.removeAttribute("game");
                    session.removeAttribute("isRunning");
                    this._scoreRepository.updateLoosingScore((String) session.getAttribute("joueur"));
                    request.setAttribute("error", "You loose");
                } else {

                    // Create a list of orders to move players
                    List<OrderDto> orders = new ArrayList<OrderDto>();
                    orders.add(new OrderDto("A",
                            request.getParameter("A") != null ? request.getParameter("A").toUpperCase()
                                    : Directions.NONE.toString()));
                    orders.add(new OrderDto("B",
                            request.getParameter("B") != null ? request.getParameter("B").toUpperCase()
                                    : Directions.NONE.toString()));
                    orders.add(new OrderDto("C",
                            request.getParameter("C") != null ? request.getParameter("C").toUpperCase()
                                    : Directions.NONE.toString()));
                    orders.add(new OrderDto("D",
                            request.getParameter("D") != null ? request.getParameter("D").toUpperCase()
                                    : Directions.NONE.toString()));
                    Random rand = new Random();

                    // Create a random direction for Mister X
                    switch (rand.nextInt(3)) {
                        case 0:
                            orders.add(new OrderDto("X", "north"));
                            break;
                        case 1:
                            orders.add(new OrderDto("X", "south"));
                            break;
                        case 2:
                            orders.add(new OrderDto("X", "east"));
                            break;
                        case 3:
                            orders.add(new OrderDto("X", "west"));
                            break;

                    }
                    game.playTurn(orders);
                    request.setAttribute("map", game.drawTable());
                }
            }
            this.getServletContext().getRequestDispatcher("/index").forward(request,
                    response);
        } else {
            response.setContentType("text/html");
            this.getServletContext().getRequestDispatcher("/index").forward(request, response);
        }
    }
}
