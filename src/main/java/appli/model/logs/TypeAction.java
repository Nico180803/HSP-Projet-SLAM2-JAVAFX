package appli.model.logs;

public class TypeAction {

    private int id;
    private String action;
    private String description;

    public TypeAction() {}

    public TypeAction(int id, String action, String description) {
        this.id = id;
        this.action = action;
        this.description = description;
    }

    @Override
    public String toString() {
        return "TypeAction{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public String getDescription() {
        return description;
    }
}
