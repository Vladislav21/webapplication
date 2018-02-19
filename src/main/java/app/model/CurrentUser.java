package app.model;

public class CurrentUser {
    private static int id;

    public CurrentUser() {
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        CurrentUser.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrentUser that = (CurrentUser) o;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}