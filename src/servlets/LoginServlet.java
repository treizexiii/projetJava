package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xml.sax.SAXParseException;

import core.Application;
import models.Compte;
import repositories.interfaces.IJoueursRepository;

public class LoginServlet extends HttpServlet {

    private IJoueursRepository _JoueursRepository;

    public LoginServlet() {
        super();
        Application app = new Application();
        this._JoueursRepository = (IJoueursRepository) app.getRepository("joueurs");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("joueur");
        session.removeAttribute("isRunning");
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            Compte joueur = this._JoueursRepository.getJoueur(login, password);

            if (joueur == null) {
                req.setAttribute("error", "Identifiant incorrect");
                this.getServletContext().getRequestDispatcher("/").forward(req, resp);
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("joueur", joueur.getLogin());
                this.getServletContext().getRequestDispatcher("/index").forward(req, resp);
            }
        } catch (SAXParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
