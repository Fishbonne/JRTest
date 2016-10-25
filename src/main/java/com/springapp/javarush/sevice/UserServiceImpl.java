package com.springapp.javarush.sevice;

import com.springapp.javarush.dao.UserDao;
import com.springapp.javarush.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Override
    public List<User> getUserBySearch(String searchString) {
        return userDao.getUserBySearch(searchString);
    }

    @Override
    public Long getUserCount() {
        return userDao.getUserCount();
    }

    @Override
    public List<User> getUserListPage(int page) {
        return userDao.getUserListPage(page);
    }
}
