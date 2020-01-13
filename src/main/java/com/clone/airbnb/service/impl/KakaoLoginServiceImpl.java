package com.clone.airbnb.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.enu.LoginMethod;
import com.clone.airbnb.exception.KakaoEmailDoesNotExistException;
import com.clone.airbnb.exception.KakaoException;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.service.KakaoLoginService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Service
public class KakaoLoginServiceImpl implements KakaoLoginService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${social_login.kakao.client_id}")
	private String client_id;
	
	private String redirect_uri = "http://localhost:8081/login/kakao/callback";
	
	@Override
	public String identityUrl() {
		return "https://kauth.kakao.com/oauth/authorize?client_id=" + client_id + "&redirect_uri=" + redirect_uri + "&response_type=code";
	}

	@Override
	public User authorize(String code) {
		URI uri;
		
		try {
			uri = new URI(
					"https://kauth.kakao.com/oauth/token?"
					+ "grant_type=authorization_code"
					+ "&client_id=" + client_id
					+ "&redirect_uri=" + redirect_uri
					+ "&code=" + code
					);
		} catch (URISyntaxException e) {
			throw new KakaoException(e);
		}
		
		HttpEntity<String> request = new HttpEntity<>(null);
		
		KakaoToken token = null;
		try {
			token = restTemplate.postForObject(uri, request, KakaoToken.class);
		} catch (Exception e) {
			throw new KakaoException(e);
		}
		
		URI profileUri = null;
		
		try {
			profileUri = new URI("https://kapi.kakao.com/v2/user/me");
		} catch (URISyntaxException e) {
			throw new KakaoException(e);
		}
		
		HttpHeaders profileRequestHeaders = new HttpHeaders();
		profileRequestHeaders.set("Authorization", "Bearer " + token.getAccess_token());
		
		HttpEntity<String> profileRequest = new HttpEntity<>(profileRequestHeaders);
		
		ResponseEntity<String> profileResult = null;
		
		try {
			profileResult = restTemplate.exchange(profileUri, HttpMethod.GET, profileRequest, String.class);
		} catch (Exception e) {
			throw new KakaoException(e);
		}
		
		String profileBody = profileResult.getBody();
		
		KakaoProfile profile = null;
		
		try {
			profile = new ObjectMapper()
					.readerFor(KakaoProfile.class)
					.readValue(profileBody);
		} catch (JsonProcessingException e) {
			throw new KakaoException(e);
		}
		
		if (profile.getEmail() == null) {
			throw new KakaoEmailDoesNotExistException("카카오 계정의 이메일이 존재하지 않습니다.");
		}
		
		SafeUser safeUser = userRepository.findByUsername(profile.getEmail(), SafeUser.class);
		
		User user = null;
		
		if (safeUser != null) {
			if (safeUser.getLoginMethod() != LoginMethod.KAKAO) {
				throw new KakaoException("\"User " + safeUser.getUsername() + " 가 Kakao 계정이 아닙니다.");
			}
			user = User.toUser(safeUser);
		} else {
			user = User.builder()
					.setUsername(profile.getEmail())
					.setPassword("none")
					.setFirstName("#")
					.setLastName(profile.getNickname())
					.setEmailSecret("")
					.setLoginMethod(LoginMethod.KAKAO)
					.build();
				userRepository.save(user);
		}
		
		return user;
	}
	
}


@Getter
@Setter
@ToString
class KakaoToken {
	private String access_token;
}


@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = { "id", "connected_at" })
class KakaoProfile {
	private String email;
	private String nickname;
	
	@JsonProperty("kakao_account")
	private void unpackKakaoAccount(Map<String, Object> kakaoAccount) {
		this.email = (String) kakaoAccount.get("email");
	}
	
	@JsonProperty("properties")
	private void unpackProperties(Map<String, Object> properties) {
		this.nickname = (String) properties.get("nickname");
	}
}
