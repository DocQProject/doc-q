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
    private final String loginId;
    private final Collection<? extends GrantedAuthority> authorities;

    @Builder
    public AuthUser(Long userId, String loginId, UserRole userRole) {
        this.userId = userId;
        this.loginId= loginId;
        this.authorities = List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    public static AuthUser of(Long userId, String loginId, UserRole userRole) {
        return AuthUser.builder()
                .userId(userId)
                .loginId(loginId)
                .userRole(userRole)
                .build();
    }
}
