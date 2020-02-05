package fr.uha.appintav.utils;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwsUtils {
	
	private Key key;
	
	private static JwsUtils INSTANCE = new JwsUtils();
	
	private JwsUtils() {
		this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}
	
	public static JwsUtils getInstance() {
		return INSTANCE;
	}
	
	public String saveToken(String value) {
		Date date = new Date();
		date.setMinutes(date.getMinutes() + 30);
		return Jwts.builder().setSubject(value).setExpiration(date).signWith(key).compact();
	}
	
	public boolean containsToken(String token) {
		try {
		    Jwts.parser().setSigningKey(key).parseClaimsJws(token);
		} catch (JwtException e) {
			return false;
		}
		return true;
	}
	
	public boolean removeToken(String token) {
		
		return false;
	}
	
}
