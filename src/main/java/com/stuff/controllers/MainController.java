package com.stuff.controllers;

import com.stuff.services.UserService;
import com.stuff.services.UsersUpload;
import com.stuff.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.util.*;

@Controller
public class MainController implements WebMvcConfigurer {

    @Autowired
    UserService userService;

    private static Set<User> users = UsersUpload.upload();

    public static Set<User> getUsers() {
        return users;
    }

    @GetMapping("/allUsers")
    public String getAllUsers(Model model){
        model.addAttribute("users",users);
        return "allUsers";
    }
    @GetMapping("/addUser")
    public String addNewUser(Model model){
        model.addAttribute("user",new User());
        return "addUser";
    }
    @PostMapping("/addUser")
    public String newUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes, @Valid User validUser, BindingResult bindingResult ){

       if (bindingResult.hasErrors()) {
            return("addUser");
        }
        return userService.addUser(user,redirectAttributes);
    }
}
