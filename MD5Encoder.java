

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoder {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
	
	/**
	 * ByteArray To Hex String
	 * 
	 * @param b byte Array
	 * @return String
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}
	
	/**
	 * Byte To Hex String
	 * 
	 * @param b byte
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	/**
	 * Encode to MD5 format
	 * 
	 * @param origin String
	 * @return String MD5
	 * @throws NoSuchAlgorithmException
	 */
	public synchronized static String encode(String origin) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String resultString = null;
		MessageDigest md = MessageDigest.getInstance("MD5");
		resultString = byteArrayToHexString(md.digest(origin.getBytes("UTF-8")));
		return resultString.toLowerCase();
	}
}
