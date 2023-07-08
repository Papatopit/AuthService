package org.authService.dto;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@RequiredArgsConstructor
public enum RoleDto implements GrantedAuthority {
    ADMIN("ADMIN"),
    USER("USER");

    private final String val;

    @Override
    public String getAuthority() {
        return val;
    }

}
