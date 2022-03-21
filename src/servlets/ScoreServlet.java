package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.Application;
import models.Scores;
import repositories.interfaces.IScoreRepository;

/**
 * WelcomeServlet
 */
public class ScoreServlet extends HttpServlet {

    private IScoreRepository _scoreRepository;

    public ScoreServlet() {
        super();
        Application app = new Application();
        this._scoreRepository = (IScoreRepository) app.getRepository("scores");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("joueur") != null) {
            List<Scores> scores = null;
            try {
                scores = this._scoreRepository.getScores();
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setAttribute("scores", scores);
            response.setContentType("text/html");
            this.getServletContext().getRequestDispatcher("/html/scores.jsp").forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher("/index").forward(request, response);
        }
    }
}
