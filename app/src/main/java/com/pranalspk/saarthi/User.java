package com.pranalspk.saarthi;

/**
 * Created by Pranal on 03-04-2018.
 */

public class User
{
    private String name;
    private String username;
    private String pass;
    private String age;
    private String contactno;
    private String soscontact;

    public User()
    {
        this.username="empty";
        this.pass="empty";

    }

    /*public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }*/

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

   /* public String getContactno() {
        return contactno;
    }
    public void setContactno(String contactno) {
        this.contactno = contactno;
    }*/

    public String getSoscontact() {
        return soscontact;
    }
    public void setSoscontact(String soscontact) {
        this.soscontact = soscontact;
    }






}
