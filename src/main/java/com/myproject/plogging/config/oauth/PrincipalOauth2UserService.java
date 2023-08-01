package com.myproject.plogging.config.oauth;


import com.myproject.plogging.config.auth.PrincipalDetails;
import com.myproject.plogging.config.custom.CustomBcryptEncoder;
import com.myproject.plogging.config.oauth.provider.GoogleUserInfo;
import com.myproject.plogging.config.oauth.provider.Oauth2UserInfo;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final CustomBcryptEncoder customBcryptEncoder;


    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("Attributes: {}", oAuth2User.getAttributes());

        Oauth2UserInfo oauth2UserInfo = null;

        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            oauth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }

        String provider = oauth2UserInfo.getProvider();
        String userId = oAuth2User.getAttribute("sub") + "_" + oauth2UserInfo.getProviderId();
        String username = oauth2UserInfo.getUsername();
        String password = customBcryptEncoder.encode(userId);
        String email = oauth2UserInfo.getEmail();
        // String role = "USER";
        String nickname = username;

       User findUser = userRepository.findByUserStrId(userId);

        if(findUser == null){
            User saveUser = new User(userId, username, nickname, password, email, "", "");

            System.out.println("저장할 아이디:" + saveUser);
            userRepository.save(saveUser);
            PrincipalDetails principalDetails = new PrincipalDetails(saveUser, oAuth2User.getAttributes());
            System.out.println("principal details: " + principalDetails);

            return principalDetails;
        }

        return super.loadUser(userRequest);
    }

}
