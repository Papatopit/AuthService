package org.authService.security.jwt;

import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.authService.dto.RoleDto;
import org.authService.security.jwt.authDto.JwtAuthentication;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setFirstName(claims.get("firstName", String.class));
        jwtInfoToken.setUsername(claims.getSubject());
        jwtInfoToken.setRole(getRoles(claims));
        return jwtInfoToken;
    }

    private static Set<RoleDto> getRoles(Claims claims) {
        final List<String> role = claims.get("role", List.class);
        return role.stream()
                .map(RoleDto::valueOf)
                .collect(Collectors.toSet());
    }

}