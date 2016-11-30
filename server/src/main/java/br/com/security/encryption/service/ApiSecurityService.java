package br.com.security.encryption.service;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.security.encryption.configuration.ConfigSecurityRSA;

@RestController
@RequestMapping(value = "/security")
public class ApiSecurityService implements Serializable {

	Logger logger = Logger.getLogger(ApiSecurityService.class);

	@Autowired
	private ConfigSecurityRSA configSecurity;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CrossOrigin("*")
	@RequestMapping(value = "/generate-key-public", method = RequestMethod.GET)
	public PublicKey generateKeyPublic() {

		PublicKey keyPublic = null;

		try {

			keyPublic = configSecurity.generateKey().getPublic();

		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		} catch (NoSuchProviderException e) {
			logger.error(e.getMessage());
		}

		return keyPublic;
	}

}
