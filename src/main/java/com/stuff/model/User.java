package com.stuff.model;

import com.stuff.controllers.MainController;
import com.stuff.services.UserService;
import com.stuff.utils.IValidation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements IValidation {

    @Autowired
    UserService userService;

    @Size(min=2 ,max=30)
    private String firstName;

    @Size(min=2 ,max=30)
    private String lastName;

    @Size(min=2 ,max=30)
    private String middleName;

    @NotNull(message = "")
    @Positive
    private int age;

    @NotNull(message = "")
    @Positive
    private double salary;

    @NotNull(message = "")
    @Email(message = "Please write correct email")
    private String email;

    @Size(min=2 ,max=100)
    private String workPlace;

    private String fullName;

    @Min(0)
    private int Id;

    public User() {
    }

    public User(List<String> userInfo){
        String[] info =  userInfo.toArray(new String[0]);
        firstName = info[1];
        lastName = info[0];
        middleName = info[2];
        age = Integer.parseInt(info[3]);
        salary = Double.parseDouble(info[4]);
        email = info[5];
        workPlace = info[6];
    }

    public User(String firstName, String lastName, String middleName, int age, double salary, String email, String workPlace) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.age = age;
        this.salary = salary;
        this.email = email;
        this.workPlace = workPlace;
    }

    public String getFullName() {
        return fullName;
    }
    public String setFullName(){
        return fullName=firstName+" "+lastName+" "+middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void saveProfile()  {

            File file = new File("B:\\myNCprojects\\SpringApplication\\users",this.hashCode()+".txt");

            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file,false);

                fw.append(this.lastName+"\n"
                        +this.firstName+"\n"
                        +this.middleName+"\n"
                        +this.age+"\n"
                        +this.salary+"\n"
                        +this.email+"\n"
                        +this.workPlace);
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Double.compare(user.salary, salary) == 0 &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(middleName, user.middleName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(workPlace, user.workPlace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, middleName, age, salary, email, workPlace);
    }


    //Validation for correct full name
    @Override
    public boolean isValid() {
        String full = firstName+lastName+middleName;
        int[] codes = full.codePoints().toArray();
            for(int code:codes){
                if(!Character.isAlphabetic(code)){
                    return false;
                }
            }
        return true;
    }

    @Override
    public boolean isAlreadyExist() {
        return MainController.getUsers().contains(this);
    }
}
