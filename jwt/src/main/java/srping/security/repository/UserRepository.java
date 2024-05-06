package srping.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import srping.security.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Boolean existsByUsername(String username);

    UserEntity findByUsername(String username);
}
