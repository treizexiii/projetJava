package models.interfaces;

import java.sql.ResultSet;

public interface IEntity {
    Object deserialize(ResultSet rawData, int columnCount);

    String serrialize();
}
