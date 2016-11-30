package br.com.security.encryption.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Teste {

	public static void main(String[] args) {

		try {

			KeyPair pair = generateKey();
			PublicKey publicKey = pair.getPublic();
			PrivateKey privateKey = pair.getPrivate();
			String valorParaCriptografia = "Lalier";
			String valorCriptografado = criptografar("RSA", publicKey, valorParaCriptografia.getBytes("UTF-8"));
			System.out.println("Valor criptografado: " + valorCriptografado);
			byte[] teste = Base64.decode(valorCriptografado);
			String valorDescriptografado = descriptografar(teste, privateKey);
			System.out.println("Valor descriptografado: " + valorDescriptografado);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static KeyPair generateKey() throws NoSuchAlgorithmException, NoSuchProviderException {

		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
		keyGen.initialize(2048, random);
		KeyPair pair = keyGen.generateKeyPair();

		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();

		System.out.println("Chave pública: " + publicKey);
		System.out.println("Chave privada: " + privateKey.getFormat());

		return pair;

	}

	public static String criptografar(String algoritmo, Key chave, byte[] dados) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		Cipher cipher = Cipher.getInstance(algoritmo);

		// encrypt the text
		cipher.init(Cipher.ENCRYPT_MODE, chave);
		byte[] encrypted = cipher.doFinal(dados);
		return Base64.encode(encrypted);

	}

	public static String descriptografar(byte[] text, Key aesKey) throws UnsupportedEncodingException {

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

		return new String(dectyptedText, "UTF-8");
	}

}
