package com.syj.olb.login;

public class Person {
    public String Name;
    public String height;
    public String weight;

    public String getName() {
        return Name;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
class User{
    String  usernmae;
    String  password;
}