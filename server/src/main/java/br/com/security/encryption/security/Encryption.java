package br.com.security.encryption.security;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Encryption {

	public static String encryption(String algoritmo, Key chave, byte[] dados) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		Cipher cipher = Cipher.getInstance(algoritmo);

		// encrypt the text
		cipher.init(Cipher.ENCRYPT_MODE, chave);
		byte[] encrypted = cipher.doFinal(dados);
		return Base64.encode(encrypted);

	}

	public static String decryption(byte[] text, Key aesKey) {

		byte[] dectyptedText = null;

		try {

			// get an RSA cipher object and print the provider
			final Cipher cipher = Cipher.getInstance("RSA");

			// decrypt the text using the private key
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			dectyptedText = cipher.doFinal(text);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new String(dectyptedText);
	}

}
