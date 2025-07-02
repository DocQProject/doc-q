package api.docq.domain.auth.service;

import api.docq.domain.auth.dto.request.SignUpRequest;
import api.docq.domain.auth.dto.response.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    public SignUpResponse signUpService(SignUpRequest signUpRequest) {
        return null;
    }
}
