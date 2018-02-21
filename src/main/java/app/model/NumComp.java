package app.model;



import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NumComp {

    private static NumComp instance = new NumComp();
    private int[] number;

    public static NumComp getInstance() {
        return instance;
    }

    public NumComp() {
        Set<Integer> genereted = new HashSet<>();
        Random random = new Random();
        while (genereted.size() < 4) {
            genereted.add(random.nextInt(9));
        }
        number = genereted.stream().mapToInt(i->i).toArray();
    }


    public int[] getNumber() {

        return number;
    }

    public void setNumber(int[] number) {
        this.number = number;
    }
}
