package ru.sberbank.edu.model;


public class MainObj {

    private String main;
    private String description;

    public MainObj(String main, String description) {
        this.main = main;
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MainObj{" +
                "main='" + main + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
