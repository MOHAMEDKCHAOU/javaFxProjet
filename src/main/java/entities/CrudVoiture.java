package entities;

import java.sql.SQLException;
import java.util.List;

public interface CrudVoiture <Voi> {
    public void ajouter(Voi v);
    public void modifier(Voi v);
    public void supprimer(int id) throws SQLException;
    public List<Voiture> Show();
    public Voiture getById(int id) throws SQLException;
}
