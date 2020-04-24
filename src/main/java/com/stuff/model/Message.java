package com.stuff.model;

import com.stuff.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.constraints.NotBlank;

@Component
public class Message {

    @Autowired
    UserService userService;

    private String email;

    private int id;

    @NotBlank
    private String subject;

    @NotBlank
    private String text;

    public Message() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String message) {
        this.text = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public boolean isValid(){
        if(this.getId()>userService.getFoundedUsers().size() || this.getId()<0){
            return false;
        }
        else return true;
    }
}
