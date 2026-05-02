package com.cyclone.dineflow.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 28-04-2026
 */
public record UserPrincipal(
        String userId,
        List<String> roles,
        Collection<? extends GrantedAuthority> authorities
)
{}
