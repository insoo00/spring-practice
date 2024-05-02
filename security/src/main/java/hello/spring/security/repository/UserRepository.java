package hello.spring.security.repository;


import hello.spring.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUsername(String username);

    UserEntity findByUsername(String username);
}
