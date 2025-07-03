package api.docq.common.dto;

import api.docq.domain.user.enums.UserRole;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class AuthUser {
    private final Long userId;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;

    @Builder
    public AuthUser(Long userId, String email, UserRole userRole) {
        this.userId = userId;
        this.email= email;
        this.authorities = List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    public static AuthUser of(Long userId, String email, UserRole userRole) {
        return AuthUser.builder()
                .userId(userId)
                .email(email)
                .userRole(userRole)
                .build();
    }
}
