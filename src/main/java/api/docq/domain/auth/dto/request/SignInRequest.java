package api.docq.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignInRequest {
    @NotBlank(message = "로그인 아이디를 입력해주세요.")
    private final String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private final String password;
}
