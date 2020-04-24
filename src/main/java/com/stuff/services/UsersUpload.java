package com.stuff.services;

import com.stuff.controllers.MainController;
import com.stuff.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsersUpload {

    private static final String UPLOAD_FOLDER ="B:\\myNCprojects\\SpringApplication\\users\\";

    public static Set<User> upload(){
        Set<User> users = new HashSet<>();
        try {
            List<List<String>> userFiles = Files.walk(Paths.get("B:\\myNCprojects\\SpringApplication\\users"))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".txt"))

                    .map(path -> {
                        try {
                            return Files.readAllLines(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }).filter(Objects::nonNull).filter(file->file.size()>1 && file.size()<8)
                    .collect(Collectors.toList());

            Iterator<List<String>> iterator = userFiles.iterator();
            while(iterator.hasNext()){
                users.add(new User(iterator.next()));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return users;
    }

    public static String userUpload(MultipartFile file){
        try{
            //File newFile = new File(UPLOAD_FOLDER+file.getOriginalFilename());
            byte[] bytes = file.getBytes();
            Files.write(Paths.get(UPLOAD_FOLDER+file.getOriginalFilename()),bytes);
            MainController.getUsers().add(new User(Files.readAllLines(Paths.get(UPLOAD_FOLDER+file.getOriginalFilename()))));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "redirect:allUsers";

    }
}
