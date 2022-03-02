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

import com.mysql.cj.x.protobuf.MysqlxCrud.Order.Direction;

import engine.Directions;
import engine.Game;
import engine.Dtos.OrderDto;

@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024
        * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class GameServlet extends HttpServlet {

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
                Part gameMap = request.getPart("game-map");
                InputStream fileContent = gameMap.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent, "UTF8"));
                String firstLine = reader.readLine();
                int X = Integer.parseInt(firstLine.split(":")[1]);
                int Y = Integer.parseInt(firstLine.split(":")[2]);
                int[][] map = new int[X][Y];
                for (int x = 0; x < map.length; x++) {
                    String[] line = reader.readLine().split(":");
                    for (int y = 0; y < map[x].length; y++) {
                        map[x][y] = Integer.parseInt(line[y + 1]);
                    }
                }
                Game game = Game.startGame(X, Y, map);
                session.setAttribute("game", game);
                request.setAttribute("map", game.drawTable());
            } else {
                Game game = (Game) session.getAttribute("game");
                if (game.getWon() == true) {
                    session.removeAttribute("game");
                    session.removeAttribute("isRunning");
                    request.setAttribute("error", "You won");
                } else if (game.getTurn() >= 4) {
                    session.removeAttribute("game");
                    session.removeAttribute("isRunning");
                    request.setAttribute("error", "You loose");
                } else {
                    List<OrderDto> orders = new ArrayList<OrderDto>();
                    orders.add(new OrderDto("A", request.getParameter("A") != null ? request.getParameter("A")
                            : Directions.NONE.toString()));
                    orders.add(new OrderDto("B", request.getParameter("B") != null ? request.getParameter("B")
                            : Directions.NONE.toString()));
                    orders.add(new OrderDto("C", request.getParameter("C") != null ? request.getParameter("C")
                            : Directions.NONE.toString()));
                    orders.add(new OrderDto("D", request.getParameter("D") != null ? request.getParameter("D")
                            : Directions.NONE.toString()));
                    Random rand = new Random();
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
