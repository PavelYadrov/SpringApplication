package com.stuff.model;

import javax.validation.constraints.Size;

public class FindForm {

    @Size(min=2,max=30)
    private String firstName;

    @Size(min=2,max=30)
    private String lastName;

    public FindForm() {
    }

    public FindForm(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
}
