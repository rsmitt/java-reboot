package ru.sberbank.edu;

public class Person implements Comparable<Person>{
    private String name;
    private String city;
    private Integer age;

    public Person(String name, String city, Integer age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o==null) return false;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!name.equalsIgnoreCase(person.name)) return false;
        if (!city.equalsIgnoreCase(person.city)) return false;
        return age.equals(person.age);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + age.hashCode();
        return result;
    }

    @Override
    public int compareTo(Person otherPerson) {
        int cityCompare = this.city.compareTo(otherPerson.city);
        if(cityCompare!=0){
            return cityCompare;
        }
        return this.name.compareTo(otherPerson.name);
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Integer getAge() {
        return age;
    }
}