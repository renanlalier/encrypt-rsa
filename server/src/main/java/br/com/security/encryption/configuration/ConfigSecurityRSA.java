package br.com.security.encryption.configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * Classe responsável pelas regras de geração do par de chaves pública e privada
 *  
 * @author Renan Lalier
 * @since 1 de dez de 2016
 * @version 1.0
 *
 */

@Configuration
public class ConfigSecurityRSA {
	
	private Logger logger = Logger.getLogger(ConfigSecurityRSA.class);
	
	@Bean
	public KeyPair generateKey() throws NoSuchAlgorithmException, NoSuchProviderException {

		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
		keyGen.initialize(2048, random);
		KeyPair pair = keyGen.generateKeyPair();

		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();
		
		logger.info("Chave pública gerada: " + publicKey);
		logger.info("Chave privada gerada: " + privateKey);

		return pair;

	}

	
}
