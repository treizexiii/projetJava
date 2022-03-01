package repositories.interfaces;

import java.sql.SQLException;

import models.Compte;

public interface IJoueursRepository {
    Compte getJoueur(String login, String password) throws SQLException, Exception;

    void AddCompte(Compte nouveauJoueur) throws InstantiationException, IllegalAccessException, SQLException;
}
