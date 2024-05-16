package services;

import entities.CrudVoiture;
import entities.Voiture;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoitureService implements CrudVoiture<Voiture> {
    public Connection conx;
    public Statement stm;

    public VoitureService() {
        conx = MyDB.getInstance().getConx();
    }

    @Override
    public void ajouter(Voiture v) {
        String req =
                "INSERT INTO voiture"
                        + "(categories,marque,couleur,prix,image)"
                        + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setString(1, v.getCategories());
            ps.setString(2, v.getMarque());
            ps.setString(3, v.getCouleur());
            ps.setInt(4, v.getPrix());
            ps.setString(5, v.getImage());
            ps.executeUpdate();
            System.out.println("Voiture Ajoutée !!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Voiture v) {
        try {
            String req = "UPDATE voiture SET categories=?, marque=?, couleur=?, prix=?, image=? WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(6, v.getId());
            pst.setString(1, v.getCategories());
            pst.setString(2, v.getMarque());
            pst.setString(3, v.getCouleur());
            pst.setInt(4, v.getPrix());
            pst.setString(5, v.getImage());
            pst.executeUpdate();
            System.out.println("Voiture Modifiée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM voiture WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Voiture suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Voiture> Show() {
        List<Voiture> list = new ArrayList<>();

        try {
            String req = "SELECT * from voiture";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Voiture(rs.getInt("id"), rs.getString("categories"),
                        rs.getString("marque"), rs.getString("couleur"),
                        rs.getInt("prix"),rs.getString("image")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Voiture getById(int id) throws SQLException {
        Voiture voi = null;
        String sql = "SELECT * FROM voiture WHERE id = ?";
        try {
            PreparedStatement pst = conx.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                voi=new Voiture(rs.getInt("id"), rs.getString("categories"),
                        rs.getString("marque"), rs.getString("couleur"),
                        rs.getInt("prix"),rs.getString("image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voi;
    }
}
