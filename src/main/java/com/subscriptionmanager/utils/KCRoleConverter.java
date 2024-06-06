package com.subscriptionmanager.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class KCRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final String clientId;

    public KCRoleConverter(@Value("${keycloak.client.business}") String clientId) {
        this.clientId = clientId;
    }

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Map<String, Object> resourceAccess = (Map<String, Object>) jwt.getClaims().get("resource_access");
        Map<String, Object> clientAccess = resourceAccess != null ? (Map<String, Object>) resourceAccess.get(clientId) : null;

        Collection<GrantedAuthority> returnValue = new ArrayList<>();
        List<?> roles = clientAccess != null ? (List<?>) clientAccess.get("roles") : null;

        if (roles == null || roles.isEmpty()) {
            return returnValue;
        }

        for (Object roleName : roles) {
            returnValue.add(new SimpleGrantedAuthority("ROLE_" + roleName));
        }

        return returnValue;
    }
}