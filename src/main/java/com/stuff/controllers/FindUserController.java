package com.stuff.controllers;

import com.stuff.model.FindForm;
import com.stuff.model.Message;
import com.stuff.model.User;
import com.stuff.services.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FindUserController {

    @Autowired
    UserService userService;

    @GetMapping("/findUser")
    public String getFindUser(Model model){
        model.addAttribute("find",new FindForm());
        return "findUser";
    }
    @PostMapping("/findUser")
    public String setUserInfo(@ModelAttribute("find") FindForm find, RedirectAttributes redirectAttributes,
                              @Valid FindForm validForm, BindingResult bindingResult, @RequestHeader(value = "user-agent") String agent){

        if(bindingResult.hasErrors()){
            return "findUser";
        }
        return userService.findUser(find,redirectAttributes,agent);
    }

    @GetMapping("/findResult")
    public String foundUsers(Model model){
        model.addAttribute("message",new Message());
        model.addAttribute("found",userService.getFoundedUsers());
        return "findResult";
    }

}
