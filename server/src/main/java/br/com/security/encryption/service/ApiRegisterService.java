package br.com.security.encryption.service;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import br.com.security.encryption.configuration.ConfigSecurityRSA;
import br.com.security.encryption.model.Message;
import br.com.security.encryption.security.Encryption;

@RestController
@RequestMapping(value = "/card")
public class ApiRegisterService {

	Logger logger = Logger.getLogger(ApiRegisterService.class);

	@Autowired
	private ConfigSecurityRSA configSecurity;

	@CrossOrigin("*")
	@RequestMapping(value = "/register-card", method = RequestMethod.POST)
	public void registerCard(@RequestBody String message) {

		Gson gson = new Gson();
		Message msg = gson.fromJson(message, Message.class);

		try {

			PrivateKey privateKey = configSecurity.generateKey().getPrivate();

			System.out.println("Mensagem criptografada: " + msg.getCipher());

			byte[] textDecoder = Base64.decode(msg.getCipher());
			String textDecryption = Encryption.decryption(textDecoder, privateKey);
			System.out.println("Texto descriptografado: " + textDecryption);

		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		} catch (NoSuchProviderException e) {
			logger.error(e.getMessage());
		}

	}

}
