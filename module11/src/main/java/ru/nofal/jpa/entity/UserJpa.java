package ru.nofal.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "users")
public class UserJpa implements Cloneable {

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

    public UserJpa() {
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
        if (!o.getClass().equals(UserJpa.class)) {
            return false;
        }
        UserJpa user = (UserJpa) o;
        return user.getId().equals(id) && user.getName().equals(name) && user.getAge().equals(age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public UserJpa clone() throws CloneNotSupportedException {
        UserJpa userJpaClone = (UserJpa) super.clone();
        userJpaClone.setId(id);
        userJpaClone.setName(name);
        userJpaClone.setAge(age);
        return userJpaClone;
    }
}
