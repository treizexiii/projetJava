import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.Application;
import models.Compte;
import repositories.interfaces.IJoueursRepository;

public class RegisterServlet extends HttpServlet {

    private IJoueursRepository _JoueursRepository;

    public RegisterServlet() {
        super();
        Application app = new Application();
        this._JoueursRepository = (IJoueursRepository) app.getRepository("joueursRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        this.getServletContext().getRequestDispatcher("/html/register-form.html").forward(request, response);
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
