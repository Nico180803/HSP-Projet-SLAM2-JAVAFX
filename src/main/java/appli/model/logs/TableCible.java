package appli.model.logs;

public class TableCible {

    private int id;
    private String nom;
    private String description;

    public TableCible() {}

    public TableCible(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    @Override
    public String toString() {
        return "TableCible{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

}
