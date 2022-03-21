package repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.interfaces.IDatabase;
import models.Scores;
import models.interfaces.IEntity;
import repositories.interfaces.IScoreRepository;

public class ScoreRepository implements IScoreRepository {
    private IDatabase _context;

    public ScoreRepository(IDatabase _context) {
        this._context = _context;
    }

    @Override
    public List<Scores> getScores() throws SQLException, Exception {
        List<IEntity> entities = this._context.select("Scores").excuteQuery().ToList();
        List<Scores> scores = new ArrayList<Scores>();
        for (IEntity entity : entities) {
            scores.add((Scores) entity);
        }
        return scores;
    }

    @Override
    public Scores getScore(String login) throws SQLException, Exception {
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("login", login);
        Scores joueur = (Scores) this._context.select("Scores").where(filters).excuteQuery().First();
        return joueur;
    }

    @Override
    public void updateWinningScore(String login) {
        Scores currentScores;
        try {
            currentScores = this.getScore(login);
            if (currentScores != null) {
                currentScores.setNbPartiesGagnees(currentScores.getNbPartiesGagnees() + 1);
                Map<String, Object> filters = new HashMap<String, Object>();
                filters.put("login", login);
                this._context.Update(currentScores).where(filters).excuteQuery();
            } else {
                currentScores = new Scores();
                currentScores.setLogin(login);
                currentScores.setNbPartiesGagnees(1);
                this._context.Insert(currentScores).excuteQuery();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLoosingScore(String login) {
        Scores currentScores;
        try {
            currentScores = this.getScore(login);
            if (currentScores != null) {
                currentScores.setNbPartiesPerdues(currentScores.getNbPartiesPerdues() + 1);
                Map<String, Object> filters = new HashMap<String, Object>();
                filters.put("login", login);
                this._context.Update(currentScores).where(filters).excuteQuery();
            } else {
                currentScores = new Scores();
                currentScores.setLogin(login);
                currentScores.setNbPartiesPerdues(1);
                this._context.Insert(currentScores).excuteQuery();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
