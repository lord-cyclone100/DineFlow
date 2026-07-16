package com.cyclone.dineflow.security;

import com.cyclone.dineflow.entity.RefreshToken;
import com.cyclone.dineflow.entity.Roles;
import com.cyclone.dineflow.entity.User;
import com.cyclone.dineflow.entity.data.UserProvider;
import com.cyclone.dineflow.entity.data.UserRoles;
import com.cyclone.dineflow.repository.RefreshTokenRepository;
import com.cyclone.dineflow.repository.RolesRepository;
import com.cyclone.dineflow.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 26-04-2026
 */
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = (String) oAuth2User.getAttributes().get("email");

        Roles roles = rolesRepository.findByRoleName(UserRoles.CUSTOMER).orElseThrow(()->new RuntimeException("Role not found"));

        // Load our user from DB
        User foundUser = userRepository.findByEmail(email)
                .map((existingUser)->{
                    if (existingUser.getProvider() == UserProvider.LOCAL) {
                        existingUser.setProvider(UserProvider.GOOGLE);
                        existingUser.setPassword(null); // optional, only if you do not want local login anymore
                        return userRepository.save(existingUser);
                    }

                    return existingUser;
                })
                .orElseGet(()->{
            User newUser = User.builder()
                    .email(email)
                    .name(oAuth2User.getName())
                    .roles(List.of(roles))
                    .provider(UserProvider.GOOGLE)
                    .build();
            return userRepository.save(newUser);
        });

        List<String> userRoles = foundUser.getRoles().stream().map(role -> role.getRoleName().name()).toList();

        // Generate JWT access token
        String accessToken = jwtUtil.buildAccessToken(foundUser.getId(),userRoles, TokenType.ACCESS);

        // Generate refresh token
        String refreshToken = jwtUtil.buildRefreshToken(foundUser.getId(),userRoles, TokenType.REFRESH);

        RefreshToken refreshTokenRecord =  RefreshToken.builder()
                .token(refreshToken)
                .user(foundUser)
                .build();

        refreshTokenRepository.save(refreshTokenRecord);

        // Set refresh token in HttpOnly cookie
//        Cookie cookie = new Cookie("refreshToken", refreshToken.getToken());
//        cookie.setHttpOnly(true);
//        cookie.setSecure(false);        // set true in production (HTTPS)
//        cookie.setPath("/");
//        cookie.setMaxAge(7 * 24 * 60 * 60);
//        response.addCookie(cookie);

        // Redirect frontend with access token as query param
        // Frontend reads it once, stores in memory, then discards from URL

//        String redirectUrl = frontendUrl + "/oauth2/callback?token=" + accessToken;

        String frontendRedirectUrl = "http://localhost:5173/oauth2/redirect?accessToken="
                + accessToken + "&refreshToken=" + refreshToken;
        getRedirectStrategy().sendRedirect(request, response, frontendRedirectUrl);
    }
}
