package ru.nofal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Cloneable {

    @Id
    @Column(name = "user_id")
    Long id;

    @Column(name = "user_name")
    String name;

    @Column(name = "user_age")
    Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!o.getClass().equals(User.class)) {
            return false;
        }
        User user = (User) o;
        return user.getId().equals(id) && user.getName().equals(name) && user.getAge().equals(age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        User useClone = (User) super.clone();
        useClone.setId(id);
        useClone.setName(name);
        useClone.setAge(age);
        return useClone;
    }
}
