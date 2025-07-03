package api.docq.domain.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpResponse {

    private String token;

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
