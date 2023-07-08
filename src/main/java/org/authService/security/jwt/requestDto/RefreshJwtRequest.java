package org.authService.security.jwt.requestDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RefreshJwtRequest {
    public String refreshToken;
}
