package api.docq.domain.user.entity;

import api.docq.common.entity.TimeStamped;
import api.docq.domain.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class Users extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clinicId;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role ;

}
