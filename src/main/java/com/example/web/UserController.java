package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.User;
import com.example.service.IUserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/add/{name}/{tel}/{sex}")
    public User addUser(@PathVariable String name,
                        @PathVariable String tel, @PathVariable int sex) {
        User user = new User();
        user.setSex(sex);
        user.setName(name);
        user.setTel(tel);
        userService.saveUser(user);
        return user;
    }

    @RequestMapping(value = "/delete/{id}")
    public void deleteBook(@PathVariable int id) {
        userService.delete(id);
    }

    @RequestMapping(value = "/")
    public List<User> getBooks() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{id}")
    public User getUser(@PathVariable int id) {
        User user = userService.findOne(id);
        return user;
    }

    @RequestMapping(value = "/search/name/{name}")
    public List<User> getBookByName(@PathVariable String name) {
        List<User> users = userService.findByName(name);
        return users;
    }

}
