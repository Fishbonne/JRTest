package com.springapp.javarush.dao;

import com.springapp.javarush.model.User;

import java.util.List;

/**
 * Created by Semen on 27.09.2016.
 */
public interface UserDao {
    public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(int id);

    public User getUserById(int id);

    public List<User> getUserList();

    public List<User> getUserBySearch(String searchString);

    public Long getUserCount();

    public List<User> getUserListPage(int page);
}
