package com.clone.airbnb.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.enu.LoginMethod;
import com.clone.airbnb.exception.GithubException;
import com.clone.airbnb.exception.GithubPrivateEmailException;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.service.GithubLoginService;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Service
public class GithubLoginServiceImpl implements GithubLoginService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${social_login.github.client_id}")
	private String client_id;
	
	@Value("${social_login.github.client_secret}")
	private String client_secret;
	
	@Override
	public String identityUrl() {
		String redirectUri = "http://localhost:8081/login/github/callback";
		return "https://github.com/login/oauth/authorize?client_id=" + client_id + "&redirect_uri=" + redirectUri + "&scope=read:user";
	}
	
	
	@Override
	public User authorize(String code) {
		URI uri;
		
		try {
			uri = new URI(
					"https://github.com/login/oauth/access_token?"
					+ "client_id=" + client_id 
					+ "&client_secret=" + client_secret 
					+ "&code=" + code
					);
		} catch (URISyntaxException e) {
			throw new GithubException("Can't get Authorization code");
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		
		HttpEntity<String> request = new HttpEntity<>(headers);
		
		GithubToken token = restTemplate.postForObject(uri, request, GithubToken.class);

		if (token.getError() != null) {
			throw new GithubException(token.getError());
		} else {
			
			URI profileRequestUri;
			try {
				profileRequestUri = new URI("https://api.github.com/user");
			} catch (URISyntaxException e) {
				throw new GithubException("Can't get your profile");
			}
			HttpHeaders profileRequestHeaders = new HttpHeaders();
			profileRequestHeaders.set("Authorization", "token " + token.getAccess_token());
			profileRequestHeaders.set("Accept", "application/json");
			
			HttpEntity<String> profileRequest = new HttpEntity<>(profileRequestHeaders);

			ResponseEntity<GithubProfile> profileResult = restTemplate.exchange(
					profileRequestUri, HttpMethod.GET, profileRequest, GithubProfile.class);
			
			GithubProfile profile = profileResult.getBody();
			
			if (profile.getEmail() == null) {
				throw new GithubPrivateEmailException("Github 계정의 Email이 private 입니다.");
			} else {
				Optional<User> opt = userRepository.findByUsername(profile.getEmail());
				
				User user = null;
				
				if (opt.isPresent()) {
					user = opt.get();
					if (user.getLoginMethod() != LoginMethod.GITHUB) {
						throw new GithubException("Please login with: " + user.getLoginMethod());
					}
					user.setPassword(null);
				} else {
					user = new User();
					user.setUsername(profile.getEmail());
					user.setPassword("none");
					user.setFirstName(profile.getName());
					user.setLastName("#");
					user.setEmailSecret("");
					user.setEmailVerified(true);
					user.setLoginMethod(LoginMethod.GITHUB);

					userRepository.save(user);
				}
				
				return user;
				
			}
		}
	}
	
}


@Getter
@Setter
@ToString
class GithubToken {
	private String access_token;
	private String token;
	private String token_type;
	private String scope;
	private String error;
}


@Getter
@Setter
@ToString
class GithubProfile {
	private String login;
	private String name;
	private String email;
	private String bio;
}