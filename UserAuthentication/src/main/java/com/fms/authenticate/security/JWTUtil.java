package com.fms.authenticate.security;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fms.authenticate.entity.UserInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	@Value("${springbootwebfluxjjwt.jjwt.secret}")
	private String secret;
	
	private String expirationTime = "28800";

	public Claims getAllClaimsFromToken(String token) {

		return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes())).parseClaimsJws(token)
				.getBody();
	}

	public String getUsernameFromToken(String token) {
		return getAllClaimsFromToken(token).getSubject();
	}

	public Date getExpirationDateFromToken(String token) {
		return getAllClaimsFromToken(token).getExpiration();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	/*
	 * public String generateToken(UserInfo user) { Map<String, Object> claims = new
	 * HashMap<>(); Mono<UserRole> roleStr =
	 * userRolerepository.findUserByRoleId(user.getRole_id());
	 * roleStr.subscribe(dashboard -> { Role r =
	 * Role.getEnumNameForValue(dashboard.getRole_name()); claims.put("role",
	 * Arrays.asList(r)); System.out.println(" role inJWTUTIL  --- " + r); });
	 * return doGenerateToken(claims, user.getFirstname()); }
	 */

	
	public String generateToken(UserInfo user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", user.getRoles());
		System.out.println(" role ---->>>>>>>>>>>>>>>>>>   " + user.getRolename());
		return doGenerateToken(claims, user.getFirstname());
	}
	
	
	private String doGenerateToken(Map<String, Object> claims, String username) {
		Long expirationTimeLong = Long.parseLong(expirationTime); // in second

		final Date createdDate = new Date();
		final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(createdDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(secret.getBytes())).compact();
	}

	public Boolean validateToken(String token) {
		return !isTokenExpired(token);
	}

}
