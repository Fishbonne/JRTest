package com.springapp.javarush.controller;

import com.springapp.javarush.model.User;
import com.springapp.javarush.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users/{page}", method = RequestMethod.GET)
    public String listUsers2(@PathVariable(value = "page") int page, Model model) {
        long rowCount = userService.getUserCount();
        int pageCount = (int) Math.ceil(rowCount / 5.0);
        model.addAttribute("count", pageCount);
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.getUserListPage(page));
        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, Model model) {
        if (user.getId() == 0) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.getUserList());
        return "redirect:/users/1";
    }

    @RequestMapping(value = "remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/users/1";
    }


    @RequestMapping(value = "remove/{id}/{searchString}", method = RequestMethod.GET)
    public String removeUserSearch(@PathVariable("id") int id, @PathVariable("searchString") String s, Model model) {
        userService.removeUser(id);
        model.addAttribute("searchString", s);
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.getUserBySearch(s));
        return "users";
    }

    @RequestMapping(value = "edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("listUsers", userService.getUserList());
        return "users";
    }

    @RequestMapping(value = "userdata/search", method = RequestMethod.GET)
    public String search(@RequestParam(value = "search") String s, Model model) {
        model.addAttribute("searchString", s);
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.getUserBySearch(s));
        return "users";
    }

}
