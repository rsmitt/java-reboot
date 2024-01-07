package ru.sberbank.edu;
/**
 * About myself
 * @author Aleksandr Kanarskiy
 */
public class GreetingImpl implements Greeting{
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    private String name;
    private String lastName;
    private String hobby;

    public GreetingImpl() {

    }
    public GreetingImpl(String name,String lastName,String hobby) {
        this.hobby = hobby;
        this.lastName = lastName;
        this.name = name;

    }

    @Override
    public String getBestHobby() {
        return this.hobby;
    }

}
