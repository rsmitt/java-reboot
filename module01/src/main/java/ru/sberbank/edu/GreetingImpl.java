package ru.sberbank.edu;

public class GreetingImpl implements  Greeting{
    private final String HOBBY="my hobby is learning Java";
    /***
     * this method does not accept anything as input and returns a string
     * @return
     */
    @Override
    public String getBestHobby() {
        return HOBBY;
    }
}
