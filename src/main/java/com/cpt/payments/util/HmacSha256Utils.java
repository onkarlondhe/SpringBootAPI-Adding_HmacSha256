package com.cpt.payments.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSha256Utils {

	private HmacSha256Utils() {
		
	}
	/**
	 * Generates an HMAC-SHA256 signature for a given JSON input using the provided secret key.
	 *
	 * @param secretKey The secret key used for generating the signature.
	 * @param jsonInput The JSON input for which the signature is to be generated.
	 * @return The HMAC-SHA256 signature of the JSON input as a Base64-encoded string.
	 * @throws NoSuchAlgorithmException If the HmacSHA256 algorithm is not available.
	 * @throws InvalidKeyException      If the provided secret key is invalid for HMAC-SHA256.
	 */
	
	public static String generateSignature(String secretKey, String jsonInput) {

		String signature = null;
		try {
			
			//create a secretKeySpec object from the secretKey
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
			Mac mac;
			
			//Initialize the HmacSHA256 Mac instance
			mac = Mac.getInstance("HmacSHA256");
			mac.init(secretKeySpec);
			
			//Compute the HmacSHA256 Signature
			byte[] signatureBytes = mac.doFinal(jsonInput.getBytes(StandardCharsets.UTF_8));
			
			//Encode the signature in base64
			 signature = Base64.getEncoder().encodeToString(signatureBytes);
			System.out.println("HmacSHA256 Signature : " + signature);
			
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
 			e.printStackTrace();
		} 
		
		return signature;
	}

}
