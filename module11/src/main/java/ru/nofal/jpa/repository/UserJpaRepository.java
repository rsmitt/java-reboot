package ru.nofal.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nofal.jpa.entity.UserJpa;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpa, Long> {
}
