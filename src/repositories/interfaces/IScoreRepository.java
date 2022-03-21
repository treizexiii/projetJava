package repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import models.Scores;

public interface IScoreRepository {
    List<Scores> getScores() throws SQLException, Exception;

    Scores getScore(String login) throws SQLException, Exception;

    void updateWinningScore(String login);

    void updateLoosingScore(String login);
}
