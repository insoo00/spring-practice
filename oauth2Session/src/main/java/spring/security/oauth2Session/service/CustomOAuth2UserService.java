package spring.security.oauth2Session.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import spring.security.oauth2Session.dto.CustomOAuth2User;
import spring.security.oauth2Session.dto.GoogleResponse;
import spring.security.oauth2Session.dto.NaverResponse;
import spring.security.oauth2Session.dto.OAuth2Response;
import spring.security.oauth2Session.entity.UserEntity;
import spring.security.oauth2Session.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("oAuth2User = " + oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }

        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        UserEntity findUser = userRepository.findByUsername(username);
        String role = "ROLE_USER";
        if (findUser == null) {
            UserEntity user = new UserEntity();
            user.setUsername(username);
            user.setEmail(oAuth2Response.getEmail());
            user.setRole(role);

            userRepository.save(user);
        } else {
            role = findUser.getRole();
            findUser.setEmail(oAuth2Response.getEmail());

            userRepository.save(findUser);
        }

        return new CustomOAuth2User(oAuth2Response, role);
    }
}

