package br.com.security.encryption.security;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * 
 * Classe responsável pelas operações de criptografia e descriptografia de dados
 *  
 * @author Renan Lalier
 * @since 1 de dez de 2016
 * @version 1.0
 *
 */
public class Encryption {

	
	public static String encryption(String algoritmo, Key chave, byte[] dados) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		Cipher cipher = Cipher.getInstance(algoritmo);

		// criptografa o texto
		cipher.init(Cipher.ENCRYPT_MODE, chave);
		byte[] encrypted = cipher.doFinal(dados);
		return Base64.encode(encrypted);

	}

	public static String decryption(byte[] text, Key aesKey) {

		byte[] dectyptedText = null;

		try {

			final Cipher cipher = Cipher.getInstance("RSA");

			// descriptografa o texto utilizando a chave privada
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			dectyptedText = cipher.doFinal(text);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new String(dectyptedText);
	}

}
