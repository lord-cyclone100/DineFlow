package com.cyclone.dineflow.security;

import com.cyclone.dineflow.repository.RolesRepository;
import com.cyclone.dineflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;


}
