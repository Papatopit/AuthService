package org.authService.security.jwt.requestDto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtRequest {
    private String login;
    private String password;
}
