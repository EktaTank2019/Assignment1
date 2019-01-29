/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */
package com.jpa.service;

/**
 *
 * @author Ekta Tank
 * @since 29 Jan, 2019
 * @version 1.0.0
 */
import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.util.Calendar;
import java.util.List;

import net.oauth.jsontoken.JsonToken;
import net.oauth.jsontoken.JsonTokenParser;
import net.oauth.jsontoken.crypto.HmacSHA256Signer;
import net.oauth.jsontoken.crypto.HmacSHA256Verifier;
import net.oauth.jsontoken.crypto.SignatureAlgorithm;
import net.oauth.jsontoken.crypto.Verifier;
import net.oauth.jsontoken.discovery.VerifierProvider;
import net.oauth.jsontoken.discovery.VerifierProviders;

import org.apache.commons.lang3.StringUtils;
//import org.bson.types.ObjectId;
import org.joda.time.DateTime;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.jpa.exception.AuthenticationException;
//import com.google.gson.stream.MalformedJsonException;

/**
 * Provides static methods for creating and verifying access tokens and such.
 *
 * @author davidm
 *
 */
public class JWTAuthentication {

	//private static final String AUDIENCE = "NotReallyImportant";
	private static final String ISSUER = "Ekta";

	private static final String SIGNING_KEY = "@@@@@@@@@@ABHI@@@@@@@@@@";

	/**
	 * Creates a json web token which is a digitally signed token that contains
	 * a payload (e.g. userId to identify the user). The signing key is secret.
	 * That ensures that the token is authentic and has not been modified. Using
	 * a jwt eliminates the need to store authentication session information in
	 * a database.
	 *
	 * @param userId
	 * @param durationDays
	 * @return
	 */
	public static String createJsonWebToken(String userId, int durationDays) {
		//Current time and signing algorithm
		Calendar cal = Calendar.getInstance();
		HmacSHA256Signer signer;
		try {
			signer = new HmacSHA256Signer(ISSUER, null, SIGNING_KEY.getBytes());
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		}

		//Configure JSON token
		JsonToken token = new net.oauth.jsontoken.JsonToken(signer);
		//     token.setAudience(AUDIENCE);
		token.setIssuedAt(new org.joda.time.Instant(cal.getTimeInMillis()));
		token.setExpiration(new org.joda.time.Instant(cal.getTimeInMillis() + 1000L * 60L * 60L * 24L * durationDays));

		//Configure request object, which provides information of the item
		JsonObject request = new JsonObject();
		request.addProperty("userId", userId);

		JsonObject payload = token.getPayloadAsJsonObject();
		payload.add("info", request);

		try {
			return token.serializeAndSign();
		} catch (SignatureException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Verifies a json web token's validity and extracts the user id and other
	 * information from it.
	 *
	 * @param token
	 * @return
	 * @throws SignatureException
	 * @throws InvalidKeyException
	 */
	public static Tokeninfo verifyToken(String token) throws AuthenticationException {
		try {
			final Verifier hmacVerifier = new HmacSHA256Verifier(SIGNING_KEY.getBytes());

			VerifierProvider hmacLocator = new VerifierProvider() {

				@Override
				public List<Verifier> findVerifier(String id, String key) {
					return Lists.newArrayList(hmacVerifier);
				}
			};
			VerifierProviders locators = new VerifierProviders();
			locators.setVerifierProvider(SignatureAlgorithm.HS256, hmacLocator);
			net.oauth.jsontoken.Checker checker = new net.oauth.jsontoken.Checker() {

				@Override
				public void check(JsonObject payload) throws SignatureException {
					// don't throw - allow anything
				}

			};
			//Ignore Audience does not mean that the Signature is ignored
			JsonTokenParser parser = new JsonTokenParser(locators,
				checker);
			JsonToken jt;

			try {
				jt = parser.verifyAndDeserialize(token);
				
			} catch (Exception  e) {
				throw new AuthenticationException("Authentication Failure");
			}
			
			JsonObject payload = jt.getPayloadAsJsonObject();
			Tokeninfo t = new Tokeninfo();
			String issuer = payload.getAsJsonPrimitive("iss").getAsString();
			String userIdString = payload.getAsJsonObject("info").getAsJsonPrimitive("userId").getAsString();
			if (issuer.equals(ISSUER) && !StringUtils.isBlank(userIdString)) {
				t.setUserId(userIdString);
				t.setIssued(new DateTime(payload.getAsJsonPrimitive("iat").getAsLong()));
				t.setExpires(new DateTime(payload.getAsJsonPrimitive("exp").getAsLong()));
				return t;
			} else {
				throw new AuthenticationException("Authentication Failure");
				//return httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "your message goes here");
			}
		} catch (InvalidKeyException e1) {
			throw new RuntimeException(e1);
		}
	}

}
