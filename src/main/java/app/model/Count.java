package app.model;

public class Count {

    private static Count instance = new Count();

    private int count;

    public static Count getInstance(){
        return instance;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
