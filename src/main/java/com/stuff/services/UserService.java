package com.stuff.services;

import com.stuff.controllers.MainController;
import com.stuff.model.FindForm;
import com.stuff.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private List<User> foundedUsers;

    public List<User> getFoundedUsers() {
        return foundedUsers;
    }

    @Autowired
    MailService mailService;

    public String addUser(User user, RedirectAttributes redirectAttributes) {
        if (user.isValid()) {
            user.setFullName();
            if (user.isAlreadyExist()) {
                redirectAttributes.addFlashAttribute("existMessage",
                        "User " + user.getFullName()
                                + " is already exist");
                return "redirect:addUser";
            }
            MainController.getUsers().add(user);
            user.saveProfile();
            redirectAttributes.addFlashAttribute("successMessage",
                    "User " + user.getFullName()
                            + " Successfully added");

            return ("redirect:allUsers");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Creation Failed. Reason - invalid Data. Please check form and try again!");
            return ("redirect:addUser");
        }
    }

    public String findUser(FindForm find, RedirectAttributes redirectAttributes, String agent) {
        List<User> foundUsers = MainController.getUsers().stream()
                .filter(user -> user.getFirstName().equals(find.getFirstName()) && user.getLastName().equals(find.getLastName()))
                .collect(Collectors.toList());
        foundedUsers = foundUsers;
        if(foundUsers.size()==1){
            mailService.getMessage().setEmail(foundUsers.get(0).getEmail());
        }
        Iterator<User> iterator = foundUsers.iterator();
        if(foundUsers.size()>1){
            int i=0;
            while(iterator.hasNext()){
                User user = iterator.next();
                user.setId(++i);
            }
        }

        if (foundUsers.size() != 0) {
            redirectAttributes.addFlashAttribute("date", new Date());
            redirectAttributes.addFlashAttribute("agent", agent);
            redirectAttributes.addFlashAttribute("successMessage", "All matches below");
            return "redirect:findResult";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Users not found!");
            return "redirect:findUser";

        }
    }
}
