package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.HashMap;

public class GreetingImpl implements Greeting{
    HashMap<String, ArrayList<String>> hashmap = new HashMap<>();
    GreetingImpl() {
    }

    public void setGreetingImpl(Person person, ArrayList<String> list) {
        this.hashmap.put(person.getName() + " " + person.getSurname(), list);
    }

    public HashMap getAllGreetingImplByPerson(Person person) {
        return this.hashmap;
    }

    @Override
    public String getBestHobby() {
        return this.hashmap.get("I am").get(0);
    }
}
