package entities;

import java.util.Date;

public class Location {
    private int id;
    private int voiture_id;
    private String nom;
    private String prenom;
    private String mail;
    private int mobile;
    private String adresse;
    private Date date_prise;
    private Date date_depot;
    private int num_cin;
    private int prix;
    private String permis_conduite;
    private String chauffeur;
    private int reponse_location_id;
    private int user_id;

    public Location(int id, int voiture_id, String nom, String prenom, String mail, int mobile, String adresse, Date date_prise, Date date_depot, int num_cin, int prix, String permis_conduite, String chauffeur, int reponse_location_id, int user_id) {
        this.id = id;
        this.voiture_id = voiture_id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mobile = mobile;
        this.adresse = adresse;
        this.date_prise = date_prise;
        this.date_depot = date_depot;
        this.num_cin = num_cin;
        this.prix = prix;
        this.permis_conduite = permis_conduite;
        this.chauffeur = chauffeur;
        this.reponse_location_id = reponse_location_id;
        this.user_id = user_id;
    }

    public Location(int voiture_id, String nom, String prenom, String mail, int mobile, String adresse, Date date_prise, Date date_depot, int num_cin, int prix, String permis_conduite, String chauffeur, int reponse_location_id, int user_id) {
        this.voiture_id = voiture_id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mobile = mobile;
        this.adresse = adresse;
        this.date_prise = date_prise;
        this.date_depot = date_depot;
        this.num_cin = num_cin;
        this.prix = prix;
        this.permis_conduite = permis_conduite;
        this.chauffeur = chauffeur;
        this.reponse_location_id = reponse_location_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoiture_id() {
        return voiture_id;
    }

    public void setVoiture_id(int voiture_id) {
        this.voiture_id = voiture_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDate_prise() {
        return date_prise;
    }

    public void setDate_prise(Date date_prise) {
        this.date_prise = date_prise;
    }

    public Date getDate_depot() {
        return date_depot;
    }

    public void setDate_depot(Date date_depot) {
        this.date_depot = date_depot;
    }

    public int getNum_cin() {
        return num_cin;
    }

    public void setNum_cin(int num_cin) {
        this.num_cin = num_cin;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getPermis_conduite() {
        return permis_conduite;
    }

    public void setPermis_conduite(String permis_conduite) {
        this.permis_conduite = permis_conduite;
    }

    public String getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(String chauffeur) {
        this.chauffeur = chauffeur;
    }

    public int getReponse_location_id() {
        return reponse_location_id;
    }

    public void setReponse_location_id(int reponse_location_id) {
        this.reponse_location_id = reponse_location_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
