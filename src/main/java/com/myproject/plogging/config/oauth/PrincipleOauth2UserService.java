package com.myproject.plogging.config.oauth;


import com.myproject.plogging.config.CustomBcryptEncoder;
import com.myproject.plogging.config.auth.PrincipalDetails;
import com.myproject.plogging.config.oauth.provider.GoogleUserInfo;
import com.myproject.plogging.config.oauth.provider.Oauth2UserInfo;
import com.myproject.plogging.controller.UserController;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class PrincipleOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    private final CustomBcryptEncoder customBcryptEncoder;

    private final UserController userController;

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println("UserRequest: " + super.loadUser(userRequest).getAttributes());

        Oauth2UserInfo oauth2UserInfo = null;

        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            oauth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }

        String provider = oauth2UserInfo.getProvider();
        String providerId = oauth2UserInfo.getProviderId();
        String username = oauth2UserInfo.getUsername();
        String password = customBcryptEncoder.encode("");
        String email = oauth2UserInfo.getEmail();
        // String role = "USER";
        String nickname = username;

       User findUser = userRepository.findByUserStrId(providerId);


        if(findUser == null){
            User saveUser = new User(providerId, username, nickname, password, email, "", "");

            System.out.println("저장할 아이디:" + saveUser);
            userRepository.save(saveUser);
            PrincipalDetails principalDetails = new PrincipalDetails(saveUser, oAuth2User.getAttributes());
            System.out.println("principal details: " + principalDetails);

            return principalDetails;
        }

        return super.loadUser(userRequest);
    }
}
