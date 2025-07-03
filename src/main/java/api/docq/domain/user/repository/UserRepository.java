package api.docq.domain.user.repository;

import api.docq.domain.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByLoginId(String loginId);
}
