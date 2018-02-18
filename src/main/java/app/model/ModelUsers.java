package app.model;

import app.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelUsers {
    private static ModelUsers instance = new ModelUsers();

    private List<User> list;

    public static ModelUsers getInstance(){
        return instance;
    }

    private ModelUsers(){
        list = new ArrayList<>();
    }

    public void add (User user){
        list.add(user);
    }

    public List<User> list(){
        return list;
    }
}
