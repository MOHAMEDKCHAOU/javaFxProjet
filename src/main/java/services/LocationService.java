package services;

import entities.CrudLocation;
import entities.Location;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationService implements CrudLocation<Location> {

    public Connection conx;
    public Statement stm;

    public LocationService() {
        conx = MyDB.getInstance().getConx();
    }
    @Override
    public void ajouter(Location l) {
        String req =
                "INSERT INTO location"
                        + "(voiture_id,nom,prenom,mail,mobile,adresse,date_prise,date_depot,num_cin,prix,permis_conduite,chauffeur,reponse_location_id,user_id)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, l.getVoiture_id());
            ps.setString(2, l.getNom());
            ps.setString(3, l.getPrenom());
            ps.setString(4, l.getMail());
            ps.setInt(5, l.getMobile());
            ps.setString(6, l.getAdresse());
            ps.setDate(7, new java.sql.Date(l.getDate_prise().getTime()));
            ps.setDate(8, new java.sql.Date(l.getDate_depot().getTime()));
            ps.setInt(9, l.getNum_cin());
            ps.setInt(10, l.getPrix());
            ps.setString(11, l.getPermis_conduite());
            ps.setString(12, l.getChauffeur());
            ps.setInt(13, l.getReponse_location_id());
            ps.setInt(14, l.getUser_id());
            ps.executeUpdate();
            System.out.println("Location Ajoutée !!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Location l) {
        try {
            String req = "UPDATE location SET voiture_id=?, nom=?, prenom=?, mail=?, mobile=?, adresse=?" +
                    ", date_prise=?, date_depot=?, num_cin=?, prix=?, permis_conduite=?, chauffeur=?" +
                    ", reponse_location_id=?, user_id=? WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(15, l.getId());
            pst.setInt(1, l.getVoiture_id());
            pst.setString(2, l.getNom());
            pst.setString(3, l.getPrenom());
            pst.setString(4, l.getMail());
            pst.setInt(5, l.getMobile());
            pst.setString(6, l.getAdresse());
            pst.setDate(7, new java.sql.Date(l.getDate_prise().getTime()));
            pst.setDate(8, new java.sql.Date(l.getDate_depot().getTime()));
            pst.setInt(9, l.getNum_cin());
            pst.setInt(10, l.getPrix());
            pst.setString(11, l.getPermis_conduite());
            pst.setString(12, l.getChauffeur());
            pst.setInt(13, l.getReponse_location_id());
            pst.setInt(14, l.getUser_id());
            pst.executeUpdate();
            System.out.println("Location Modifiée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM location WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Location suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Location> Show() {
        List<Location> list = new ArrayList<>();

        try {
            String req = "SELECT * from location";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Location(rs.getInt("id"), rs.getInt("voiture_id"),
                        rs.getString("nom"), rs.getString("prenom"),
                        rs.getString("mail"),rs.getInt("mobile"),
                        rs.getString("adresse"),rs.getDate("date_prise"),
                        rs.getDate("date_depot"),rs.getInt("num_cin"),
                        rs.getInt("prix"),rs.getString("permis_conduite"),
                        rs.getString("chauffeur"),rs.getInt("reponse_location_id"),
                        rs.getInt("user_id")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Location getById(int id) throws SQLException {
        Location loc = null;
        String sql = "SELECT * FROM location WHERE id = ?";
        try {
            PreparedStatement pst = conx.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                loc=new Location(rs.getInt("id"), rs.getInt("voiture_id"),
                        rs.getString("nom"), rs.getString("prenom"),
                        rs.getString("mail"),rs.getInt("mobile"),
                        rs.getString("adresse"),rs.getDate("date_prise"),
                        rs.getDate("date_depot"),rs.getInt("num_cin"),
                        rs.getInt("prix"),rs.getString("permis_conduite"),
                        rs.getString("chauffeur"),rs.getInt("reponse_location_id"),
                        rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loc;
    }
}
