package com.cyclone.dineflow.security;

import com.cyclone.dineflow.entity.Roles;
import com.cyclone.dineflow.entity.User;
import com.cyclone.dineflow.entity.data.UserRoles;
import com.cyclone.dineflow.repository.RolesRepository;
import com.cyclone.dineflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 2. Extract profile from Google response
        OAuth2UserInfo userInfo = new OAuth2UserInfo(oAuth2User.getAttributes());

        // 3. Check if user exists in DB
        Optional<User> existingUser = userRepository.findByEmail(userInfo.getEmail());

        User user;
        if (existingUser.isPresent()) {
            user = existingUser.get();
            user.setName(userInfo.getName());
            user.setEmail(userInfo.getEmail());
            userRepository.save(user);
        }
        else{
            Roles roles = rolesRepository.findByRoleName(UserRoles.CUSTOMER).orElseThrow(()->new RuntimeException("Role not found"));
            user = User.builder()
                    .name(userInfo.getName())
                    .email(userInfo.getEmail())
                    .roles(List.of(roles))
                    .build();
            userRepository.save(user);
        }
        return oAuth2User;
    }
}
