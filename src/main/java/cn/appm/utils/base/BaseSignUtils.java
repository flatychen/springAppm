package cn.appm.utils.base;

import org.apache.commons.codec.digest.DigestUtils;

public class BaseSignUtils {

	/**
	 * MD5
	 * @author flatychen
	 * @date 2014-4-14
	 * @param data
	 * @return
	 */
	public static String encryptMd5(String data) {
		return DigestUtils.md5Hex(data);
	}

	/**
	 * SHA1
	 * @author flatychen
	 * @date 2014-4-14
	 * @param data
	 * @return
	 */
	public static String encryptSha1(String data) {
		return DigestUtils.sha1Hex(data);
	}

	
	/**
	 * SHA384
	 * @author flatychen
	 * @date 2014-4-14
	 * @param data
	 * @return
	 */
	public static String encryptSha384(String data) {
		return DigestUtils.sha384Hex(data);
	}
	
	/**
	 * SHA512
	 * @author flatychen
	 * @date 2014-4-14
	 * @param data
	 * @return
	 */
	public static String encryptSha512(String data) {
		return DigestUtils.sha512Hex(data);
	}

}
