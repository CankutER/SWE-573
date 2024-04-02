package com.communitter.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;

    public User getUserInfo(Long id){
        return userRepo.findById(id).orElseThrow();
    }
}
