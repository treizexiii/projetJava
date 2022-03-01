package repositories;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import core.interfaces.IDatabase;
import models.Compte;
import repositories.interfaces.IJoueursRepository;

public class JoueursRepository implements IJoueursRepository {
    private IDatabase _context;

    public JoueursRepository(IDatabase database) {
        this._context = database;
    }

    @Override
    public Compte getJoueur(String login, String password) throws SQLException, Exception {
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("Login", login);
        filters.put("Motdepasse", password);
        Compte joueur = (Compte) this._context.select("Compte").where(filters).excuteQuery().First();
        return joueur;
    }

    @Override
    public void AddCompte(Compte nouveauJoueur) throws InstantiationException, IllegalAccessException, SQLException {
        // Map<String, Object> filters = new HashMap<String, Object>();
        // filters.put("Nom", nouveauJoueur.getNom());
        // filters.put("Prenom", nouveauJoueur.getPrenom());
        // filters.put("Age", nouveauJoueur.getAge());
        // filters.put("Login", nouveauJoueur.getLogin());
        // filters.put("Motdepasse", nouveauJoueur.getMotdepasse());
        this._context.Insert(nouveauJoueur).excuteQuery();
    }

}
