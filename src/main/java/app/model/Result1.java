package app.model;

import java.util.ArrayList;
import java.util.List;

public class Result1 {

    private static Result1 instance = null;
    private List<String> result = new ArrayList<>();

    private Result1() {

    }

    public static Result1 getInstance() {
        return instance != null ? instance : new Result1();
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
