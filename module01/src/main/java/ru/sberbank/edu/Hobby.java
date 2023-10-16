package ru.sberbank.edu;

public class Hobby {
    String hobbyName;
    String hobbyDescription;

    Hobby(String hobbyName, String hobbyDescription) {
        this.hobbyName = hobbyName;
        this.hobbyDescription = hobbyDescription;
    }

    public void setHobbyName(String n) {
        this.hobbyName = n;
    }

    public String getHobbyName() {
        return this.hobbyName;
    }

    public void setHobbyDescription(String d) {
        this.hobbyDescription = d;
    }

    public String getHobbyDescription() {
        return this.hobbyDescription;
    }

    @Override
    public String toString() {
        return "hobbyName=" + hobbyName
                + ", hobbyDescription=" + hobbyDescription + "]";
    }
}
