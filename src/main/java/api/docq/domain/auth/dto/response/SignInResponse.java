package api.docq.domain.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInResponse {
    private final String token;

    @Builder
    public SignInResponse(String token) {
        this.token = token;
    }

    public static SignInResponse of(String accessToken) {
        return SignInResponse.builder()
                .token(accessToken)
                .build();
    }
}
