package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.Application;
import models.Compte;
import repositories.interfaces.IJoueursRepository;

public class RegisterServlet extends HttpServlet {

    private IJoueursRepository _JoueursRepository;

    public RegisterServlet() {
        super();
        Application app = new Application();
        this._JoueursRepository = (IJoueursRepository) app.getRepository("joueurs");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("joueur") != null) {
            this.getServletContext().getRequestDispatcher("/index").forward(request, response);
        } else {
            response.setContentType("text/html");
            this.getServletContext().getRequestDispatcher("/html/register-form.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Compte nouveauJoueur = new Compte();
            nouveauJoueur.setNom(req.getParameter("nom"));
            nouveauJoueur.setPrenom(req.getParameter("prenom"));
            nouveauJoueur.setAge(Integer.parseInt(req.getParameter("age")));
            nouveauJoueur.setLogin(req.getParameter("login"));
            nouveauJoueur.setMotdepasse(req.getParameter("motdepasse"));
            _JoueursRepository.AddCompte(nouveauJoueur);
            resp.setContentType("test/html");
            this.getServletContext().getRequestDispatcher("/index").forward(req, resp);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
