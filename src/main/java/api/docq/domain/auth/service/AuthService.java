package api.docq.domain.auth.service;

import api.docq.config.security.JwtProvider;
import api.docq.domain.auth.dto.request.SignUpRequest;
import api.docq.domain.auth.dto.response.SignUpResponse;
import api.docq.domain.user.repository.UserRepository;
import api.docq.domain.user.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static api.docq.domain.user.enums.UserRole.ROLE_USER;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public SignUpResponse signUpService(SignUpRequest signUpRequest) {
        if (!signUpRequest.getPassword().equals(signUpRequest.getCheckPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        if (userRepository.existsByLoginId(signUpRequest.getLoginId())) {
            throw new RuntimeException("이미 존재하는 회원입니다.");
        }

        if (ROLE_USER.equals(signUpRequest.getRole()) && signUpRequest.getClinicId() != null ) {
            throw new RuntimeException("병원 정보는 의사만 가질 수 있는 필드입니다.");
        }

        String encodePassword = passwordEncoder.encode(signUpRequest.getPassword());

        Users users = Users.of(
                signUpRequest.getClinicId(),
                signUpRequest.getLoginId(),
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                encodePassword,
                signUpRequest.getRole()
        );

        userRepository.save(users);
        String accessToken = jwtProvider.createAccessToken(users.getId(), users.getEmail(), users.getRole());

        return SignUpResponse.of(accessToken);
    }
}
