package app.model;

import java.util.ArrayList;
import java.util.List;

public class Lelek {

    private static Lelek instance = null;
    private List<String> result = new ArrayList<>();

    private Lelek() {

    }

    public static Lelek getInstance() {
        return instance != null ? instance : new Lelek();
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
