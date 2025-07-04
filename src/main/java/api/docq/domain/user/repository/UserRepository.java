package api.docq.domain.user.repository;

import api.docq.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginId(String loginId);

    @Query("SELECT u FROM User u WHERE u.isDeleted IS NOT true")
    Optional<User> findByLoginId(String loginId);
}
