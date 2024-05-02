package spring.security.oauth2Session.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.security.oauth2Session.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
