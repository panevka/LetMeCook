package com.letmecook.backend.user;

import java.math.BigInteger;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class OAuth2UserDiscordDto {
    BigInteger id; // should be treated as unsigned to prevent overflow
    String username;
    String globalName;
    String avatarId;
}
