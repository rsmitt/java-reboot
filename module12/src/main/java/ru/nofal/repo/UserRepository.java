package ru.nofal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nofal.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
