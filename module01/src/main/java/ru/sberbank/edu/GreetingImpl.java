package ru.sberbank.edu;

public class GreetingImpl implements Greeting {
    /***
     * В данном методе мы всегда выводим как лучшее хобби Водный туризм
     */
    public void main(String[] args) {

        System.out.println(getBestHobby());

    }

    @Override
    public String getBestHobby() {

        return "Водный туризм";

    }
}
