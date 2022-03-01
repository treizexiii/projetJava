import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.Application;
import models.Compte;
import repositories.interfaces.IJoueursRepository;

/**
 * WelcomeServlet
 */
public class WelcomeServlet extends HttpServlet {

    private IJoueursRepository _JoueursRepository;

    public WelcomeServlet() {
        super();
        Application app = new Application();
        this._JoueursRepository = (IJoueursRepository) app.getRepository("joueursRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        this.getServletContext().getRequestDispatcher("/index").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            Compte joueur = this._JoueursRepository.getJoueur(login, password);
            req.setAttribute("joueur", joueur);
            this.getServletContext().getRequestDispatcher("/menu").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}