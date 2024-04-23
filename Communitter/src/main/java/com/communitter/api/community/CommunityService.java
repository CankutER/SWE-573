package com.communitter.api.community;

import com.communitter.api.user.Role;
import com.communitter.api.user.RoleRepository;
import com.communitter.api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public Community createCommunity(Community community){
        User creator= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        community.setCreator(creator);
        Community createdCommunity=communityRepository.save(community);
        SubscriptionKey subsKey= new SubscriptionKey(creator.getId(), createdCommunity.getId());
        Role creatorRole= roleRepository.findByName("creator").orElseThrow();
        subscriptionRepository.save(new Subscription(subsKey,creator,createdCommunity,creatorRole));
        return createdCommunity;
    }

    public Community getCommunity(Long id){
        return communityRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Subscription subscribeToCommunity(Long id){
        User subscriber= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Community community = communityRepository.findById(id).orElseThrow();
        SubscriptionKey subsKey= new SubscriptionKey(subscriber.getId(), community.getId());
        Role userRole= roleRepository.findByName("user").orElseThrow();
        return subscriptionRepository.save(new Subscription(subsKey,subscriber,community,userRole));
    }
}
