package com.cpt.payments;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
 

public class TestMain {

	public static void main(String[] args) {

		String secretKey = "ecom-ct-secret123";
		String jsonInput = "{\"data\": \"your_json_data_here\"}";

		
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
			String signature = Base64.getEncoder().encodeToString(signatureBytes);
			System.out.println("HmacSHA256 Signature : " + signature);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
}
