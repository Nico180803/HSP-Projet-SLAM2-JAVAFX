package appli.model.principal;

public class Chambre {
    private int  id;
    private String num;
    private boolean estOccupe;

    public Chambre() {}

    public Chambre(String num, boolean estOccupe) {
        this.num = num;
        this.estOccupe = estOccupe;
    }

    public Chambre(int id, String num, boolean estOccupe) {
        this.id = id;
        this.num = num;
        this.estOccupe = estOccupe;
    }

    @Override
    public String toString() {
        return "Chambre{" +
                "id=" + id +
                ", num='" + num + '\'' +
                ", estOccupe=" + estOccupe +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean isEstOccupe() {
        return estOccupe;
    }

    public void setEstOccupe(boolean estOccupe) {
        this.estOccupe = estOccupe;
    }
}
