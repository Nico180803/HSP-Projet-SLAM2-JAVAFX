package appli.model.enums;

public enum Dangerosite {
    UN("1"),
    DEUX("2"),
    TROIS("3"),
    QUATRE("4"),
    CINQ("5");

    private final String niveau;

    Dangerosite(String niveau) {
        this.niveau = niveau;
    }

    public String getNiveau() {
        return niveau;
    }

    public static Dangerosite fromSql(String niveau) {
        for (Dangerosite g : Dangerosite.values()) {
            if (g.niveau.equals(niveau)) {
                return g;
            }
        }
        throw new IllegalArgumentException("niveau "+ niveau+" inconnu");
    }
}
