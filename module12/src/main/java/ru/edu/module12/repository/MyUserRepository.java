package ru.edu.module12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.module12.entity.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {

}
