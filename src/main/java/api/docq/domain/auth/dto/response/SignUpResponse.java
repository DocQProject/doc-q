package api.docq.domain.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class SignUpResponse {

    private final String token;

    @Builder
    public SignUpResponse (String token) {
        this.token = token;
    }

    public static SignUpResponse of(String accessToken) {
        return SignUpResponse.builder()
                .token(accessToken)
                .build();
    }
}
