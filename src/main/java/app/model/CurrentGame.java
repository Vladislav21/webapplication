package app.model;

public class CurrentGame {
    private static int id;

    public CurrentGame() {
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        CurrentGame.id = id;
    }

}
