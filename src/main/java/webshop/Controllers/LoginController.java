package webshop.Controllers;


import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import webshop.UserData;

import java.security.Principal;
import java.util.Date;
import java.util.Map;

@RestController

class LoginController {




    @GetMapping(value = "/api/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserData handleUserInfoRequest(Principal principal) {
        UserData user = new UserData();
        System.out.println(principal.getName().toLowerCase()+"ddddddddddddddd");

        if (principal instanceof KeycloakPrincipal) {

            KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            AccessToken token = kp.getKeycloakSecurityContext().getToken();
            System.out.println("tokennnnnnnnnnn"+token.toString());
            user.setId(token.getId());
            user.setUserName(token.getName());
            Map<String, Object> otherClaims = token.getOtherClaims();
            user.setCustomAttributes(otherClaims);
        }
        System.out.println(user.getUserName()+"neveeee");
    return user;
    }

    @GetMapping(path = "/api/public")
    public String getPublic(){
        return "this is a public site "+new Date().toString();
    }

    @GetMapping(path = "/api/private")
    public String getPrivate(){
        return "this is a private site "+new Date().toString();
    }


}
