package com.stuff.controllers;


import com.stuff.model.Message;
import com.stuff.model.User;
import com.stuff.services.MailService;
import com.stuff.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class EmailController {

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @PostMapping("/findResult")
    public String sendMessage(@ModelAttribute("message") Message message, RedirectAttributes redirectAttributes,
                              @Valid Message mess, BindingResult bindingResult){
       if(bindingResult.hasErrors()){
            return "findResult";
        }
        if(message.isValid()){
            redirectAttributes.addFlashAttribute("errorMessage","Invalid user id");
            return "redirect:findResult";
        }
        mailService.send(userService.getFoundedUsers().get(message.getId()).getEmail()
                ,message.getSubject()
                ,message.getText());

        redirectAttributes.addFlashAttribute("successMessage","Email Successfully send");
        return "redirect:findUser";
    }
}
