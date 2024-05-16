package entities;

import java.sql.SQLException;
import java.util.List;

public interface CrudLocation <Loc> {
    public void ajouter(Loc l);
    public void modifier(Loc l);
    public void supprimer(int id) throws SQLException;
    public List<Location> Show();
    public Location getById(int id) throws SQLException;
}
