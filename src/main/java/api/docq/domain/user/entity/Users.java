package api.docq.domain.user.entity;

import api.docq.common.entity.TimeStamped;
import api.docq.domain.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
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
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role ;

    @Builder
    public Users (Long clinicId, String loginId, String name, String email, String password, UserRole role) {
        this.clinicId = clinicId;
        this.loginId = loginId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static Users of(Long clinicId, String loginId, String name, String email, String password, UserRole role) {
        return Users.builder()
                .clinicId(clinicId)
                .loginId(loginId)
                .name(name)
                .email(email)
                .password(password)
                .role(role)
                .build();
    }

}
