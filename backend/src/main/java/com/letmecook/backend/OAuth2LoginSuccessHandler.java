package com.letmecook.backend;

import com.letmecook.backend.user.OAuth2UserDiscordDto;
import com.letmecook.backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

	private final JwtUtils jwtUtils;
	private final UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication) throws IOException {

		var oauthUser = (org.springframework.security.oauth2.core.user.OAuth2User) authentication
				.getPrincipal();

		String id = oauthUser.getAttribute("id");
		String globalName = oauthUser.getAttribute("global_name");
		String avatar = oauthUser.getAttribute("avatar");

		BigInteger discordId = new BigInteger(id);
		Long longDiscordId = Long.parseLong(id);

		// Check if user exists
		var existingUserOpt = userService.findByDiscordId(longDiscordId);

		if (!existingUserOpt.isPresent()) {
			userService.createUser(OAuth2UserDiscordDto.builder()
					.id(discordId)
					.globalName(globalName)
					.avatarId(avatar)
					.build());
		}

		UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(globalName)
				.password("")
				.authorities("ROLE_USER")
				.build();

		var jwt = jwtUtils.generateJwtToken(
				new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities()));

		response.setContentType("application/json");
		response.getWriter().write("""
				{
				    "token": "%s",
				    "tokenType": "Bearer"
				}
				""".formatted(jwt));
	}
}
