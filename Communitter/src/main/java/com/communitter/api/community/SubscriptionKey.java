package com.communitter.api.community;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class SubscriptionKey implements Serializable {
    private Long userId;
    private Long communityId;


}