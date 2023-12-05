package ru.sberbank.edu;

/**
 * <p>Реализация интерфейса Greeting</p>
 */
public class GreetingImpl implements Greeting{
private final String bestHobby;

    /**
     *<p>Конструктор класса</p>
     * @param bestHobby хобби
     */
    public GreetingImpl(String bestHobby) {
        this.bestHobby = bestHobby;
    }

    /**
     *<p>Getter хобби</p>
     * @return хобби
     */
    @Override
    public String getBestHobby() {
        return bestHobby;
    }
}
