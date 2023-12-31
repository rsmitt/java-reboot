package ru.sberbank.edu.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.edu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query(value="insert into users (id, name, age) VALUES (:userId, :userName, :userAge)", nativeQuery=true)
    void saveOrUpdate(@Param("userId") long userId, @Param("userName") String userName, @Param("userAge") int userAge);
}