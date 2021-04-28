package com.utkarsh.practice;

public class User {

    public String userName, userage, useremail;


    public User() { }

    public User(String Name, String Age, String email){
        this.userName = Name;
        this.userage = Age;
        this.useremail = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
}
