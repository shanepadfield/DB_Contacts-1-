package com.example.mshehab.db_contacts;
/**
 * Created by mshehab on 11/20/17.
 */

public class Person {
    String name, email, phone, dept;
    int picID;
    long id;
    public String key;

    public Person() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getPicID() {
        return picID;
    }

    public void setPicID(int picID) {
        this.picID = picID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person(String name, String email, String phone, String dept, int picID) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dept = dept;
        this.picID = picID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dept='" + dept + '\'' +
                ", picID=" + picID +
                ", id=" + id +
                ", key='" + key + '\'' +
                '}';
    }
}
