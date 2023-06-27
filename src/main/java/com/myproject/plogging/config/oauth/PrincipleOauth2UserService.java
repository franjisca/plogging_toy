package com.myproject.plogging.config.oauth;


import com.myproject.plogging.config.CustomBcryptEncoder;
import com.myproject.plogging.config.auth.PrincipalDetails;
import com.myproject.plogging.config.oauth.provider.GoogleUserInfo;
import com.myproject.plogging.config.oauth.provider.Oauth2UserInfo;
import com.myproject.plogging.domain.User;
import com.myproject.plogging.repository.UserRepository;
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

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println("UserRequest: " + super.loadUser(userRequest).getAttributes());

        Oauth2UserInfo oauth2UserInfo = null;

        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            System.out.println("구글 로그인 요청");
            oauth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }

        String provider = oauth2UserInfo.getProvider();
        String providerId = oauth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;
        String password = customBcryptEncoder.encode("");
        String email = oauth2UserInfo.getEmail();
        String role = "USER";
        String nickname = username;

       User user = userRepository.findByUsername(username);


        if(user == null){
            User saveUser = new User(providerId, username, username, password, email, "", "");

            System.out.println("저장할 아이디:" + saveUser);
            userRepository.save(saveUser);


            // 회원가입 강제로 진행
            return new PrincipalDetails(saveUser, oAuth2User.getAttributes());
        }

        return super.loadUser(userRequest);
    }

}
