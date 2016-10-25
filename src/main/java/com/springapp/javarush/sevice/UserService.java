package com.springapp.javarush.sevice;

import com.springapp.javarush.model.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(int id);

    public User getUserById(int id);

    public List<User> getUserList();

    public List<User> getUserBySearch(String searchString);

    public Long getUserCount();

    public List<User> getUserListPage(int page);

}
