package entities;

public class Voiture {
    private int id;
    private String categories;
    private String marque;
    private String couleur;
    private int prix;
    private String image;

    public Voiture(int id, String categories, String marque, String couleur, int prix, String image) {
        this.id = id;
        this.categories = categories;
        this.marque = marque;
        this.couleur = couleur;
        this.prix = prix;
        this.image = image;
    }

    public Voiture(String categories, String marque, String couleur, int prix, String image) {
        this.categories = categories;
        this.marque = marque;
        this.couleur = couleur;
        this.prix = prix;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
