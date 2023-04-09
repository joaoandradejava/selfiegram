package com.joaoandradejava.selfiegram.core.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Autowired
	private JwtConfigProperty jwtConfigProperty;

	public String gerarTokenJwt(Long id) {
		return Jwts.builder().setSubject(id.toString())
				.setExpiration(
						new Date(System.currentTimeMillis() + this.jwtConfigProperty.getTempoExpiracaoTokenJwt()))
				.signWith(SignatureAlgorithm.HS512, this.jwtConfigProperty.getSenhaTokenJwt().getBytes()).compact();
	}

	public boolean isTokenJwtValido(String tokenJwt) {
		Claims claims = getClaims(tokenJwt);
		if (claims != null) {
			Date now = new Date();
			Date expiration = claims.getExpiration();
			String subject = claims.getSubject();

			if (StringUtils.hasLength(subject) && expiration != null && now.before(expiration)) {
				return true;
			}
		}

		return false;
	}

	public Long getSubject(String tokenJwt) {
		Claims claims = getClaims(tokenJwt);

		if (claims != null) {
			return Long.parseLong(claims.getSubject());
		}

		return null;
	}

	private Claims getClaims(String tokenJwt) {
		try {
			return Jwts.parser().setSigningKey(this.jwtConfigProperty.getSenhaTokenJwt().getBytes())
					.parseClaimsJws(tokenJwt).getBody();
		} catch (Exception e) {
			return null;
		}
	}
}
