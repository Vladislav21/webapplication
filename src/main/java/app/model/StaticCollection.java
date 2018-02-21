package app.model;

public class StaticCollection {
    private static int gameId = 0;

    public static int getGameId() {
        return gameId;
    }

    public static void setGameId(int gameId) {
        StaticCollection.gameId = gameId;
    }
}
