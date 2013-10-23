package StockTradingServer;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.Cipher;

public class DataEncryptor {
	// to be moved to Keystore or config file
	private String IV;
	
	public String getIV() {
		return this.IV;
	}

	public void setIV(String iV) {
		IV = iV;
	}

	public byte[] encrypt(String plainText, String encryptionKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes(), "AES");
		cipher.init(Cipher.ENCRYPT_MODE, key,
				new IvParameterSpec(this.getIV().getBytes()));
		return cipher.doFinal(plainText.getBytes());
	}

	public String decrypt(byte[] cipherText, String encryptionKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes(), "AES");
		cipher.init(Cipher.DECRYPT_MODE, key,
				new IvParameterSpec(this.getIV().getBytes()));
		return new String(cipher.doFinal(cipherText));
	}

}
