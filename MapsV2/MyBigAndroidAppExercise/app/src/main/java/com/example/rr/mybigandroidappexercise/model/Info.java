package com.example.rr.mybigandroidappexercise.model;

/**
 * Created by Jay-Ar Gabriel on 4/27/2017.
 */

public class Info {

    private String id;
    private String firstName;
    private String lastName;
    private String telephone;
    private String email;
    private String address;

    public Info(String id, String firstName, String lastName, String address, String telephone, String email) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setTelephone(telephone);
        setEmail(email);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
