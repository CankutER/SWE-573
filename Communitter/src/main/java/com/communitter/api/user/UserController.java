package com.communitter.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("@authorizer.authorizerForUser(#root,#id)")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserInfo(@P ("id") @PathVariable Long id){
        return ResponseEntity.ok(userService.getUserInfo(id));
    }

    @GetMapping()
    public ResponseEntity<String> getUserInfo(){
        return ResponseEntity.ok("hello");
    }
}
