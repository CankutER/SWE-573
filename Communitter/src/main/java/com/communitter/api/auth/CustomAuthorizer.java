package com.communitter.api.auth;

import com.communitter.api.authconfig.JwtAuthFilter;
import com.communitter.api.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component("authorizer")
public class CustomAuthorizer {

    public Logger logger = LoggerFactory.getLogger(CustomAuthorizer.class);
    public boolean authorizerForUser(MethodSecurityExpressionOperations operations, Long id){
        Authentication authentication=operations.getAuthentication();
        User principal= (User)authentication.getPrincipal();
        Long principalId = principal.getId();
        logger.info(principalId.toString());
        logger.info(String.valueOf(principalId.equals(id)));
        return principalId.equals(id);
    }


}
