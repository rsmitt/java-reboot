package ru.edu.module12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.module12.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
