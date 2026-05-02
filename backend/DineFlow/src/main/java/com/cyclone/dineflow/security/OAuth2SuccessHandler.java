//package com.cyclone.dineflow.security;
//
//import com.cyclone.dineflow.entity.User;
//import com.cyclone.dineflow.repository.UserRepository;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
///**
// * [Detailed description of the class's responsibility]
// * * @author 2480010
// *
// * @version 1.0
// * @since 26-04-2026
// */
//@Component
//@RequiredArgsConstructor
//public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//    private final JwtUtil jwtUtil;
//    private final UserRepository userRepository;
//
//    private String frontendUrl;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        Authentication authentication) throws IOException {
//
//        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//        String email = (String) oAuth2User.getAttributes().get("email");
//
//        // Load our user from DB
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Generate JWT access token
//        String accessToken = jwtUtil.buildAccessToken();
//
//        // Generate refresh token
//        String refreshToken = jwtUtil.buildRefreshToken();
//
//        // Set refresh token in HttpOnly cookie
//        Cookie cookie = new Cookie("refreshToken", refreshToken.getToken());
//        cookie.setHttpOnly(true);
//        cookie.setSecure(false);        // set true in production (HTTPS)
//        cookie.setPath("/");
//        cookie.setMaxAge(7 * 24 * 60 * 60);
//        response.addCookie(cookie);
//
//        // Redirect frontend with access token as query param
//        // Frontend reads it once, stores in memory, then discards from URL
//        String redirectUrl = frontendUrl + "/oauth2/callback?token=" + accessToken;
//        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
//    }
//}
