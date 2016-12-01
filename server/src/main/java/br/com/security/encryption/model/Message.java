package br.com.security.encryption.model;

import java.io.Serializable;

/**
 * 
 * Classe responsável por representar o objeto referente
 * a mensagem criptograda
 *  
 * @author Renan Lalier
 * @since 1 de dez de 2016
 * @version 1.0
 *
 */
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cipher;

	public String getCipher() {
		return cipher;
	}

	public void setCipher(String cipher) {
		this.cipher = cipher;
	}

}
