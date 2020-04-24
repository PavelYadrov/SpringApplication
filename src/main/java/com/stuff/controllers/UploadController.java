package com.stuff.controllers;

import com.stuff.model.User;
import com.stuff.services.UsersUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;





@Controller
public class UploadController {
    @PostMapping("/addUsers")
    public String uploadFile(@RequestParam("file") MultipartFile file){
        return UsersUpload.userUpload(file);
    }
}



