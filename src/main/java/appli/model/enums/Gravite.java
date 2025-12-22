package appli.model.enums;

public enum Gravite {
    UN("1"),
    DEUX("2"),
    TROIS("3"),
    QUATRE("4"),
    CINQ("5");

    private final String niveau;

    Gravite(String niveau) {
        this.niveau = niveau;
    }

    public String getNiveau() {
        return niveau;
    }

    public Gravite fromSql(String niveau) {
        for (Gravite g : Gravite.values()) {
            if (g.niveau.equals(niveau)) {
                return g;
            }
        }
        throw new IllegalArgumentException("niveau "+ niveau+" inconnu");
    }
}
