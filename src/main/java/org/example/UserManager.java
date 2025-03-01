package org.example;

import java.util.HashSet;
import java.util.Set;

public class UserManager {
    private Set<String> users;
    public UserManager(){
        this.users = new HashSet<>();
    }
    public void addUser(String username){
        users.add(username);
    }
    public void removeUser(String username){
        users.remove(username);
    }
    public int getUserCount(){
        return users.size();
    }
    public boolean userExist(String username){
        return users.contains(username);
    }
    public void clearUsers(){
        users.clear();
    }
}
