package top.ryan1h.springcloud.template.common.util.crypto.digest;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * 摘要算法工具类
 */
public class DigestUtils {
    private static final String ENCODING = "UTF-8";

    /**
     * 使用消息摘要算法编码
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, MessageDigestEnum messageDigestEnum) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(messageDigestEnum.getName());
        messageDigest.update(data.getBytes(ENCODING));
        byte[] digest = messageDigest.digest();

        return Base64.encodeBase64String(digest);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(DigestUtils.encrypt("abc", MessageDigestEnum.MD5));
        System.out.println(DigestUtils.encrypt("abc", MessageDigestEnum.SHA1));
        System.out.println(DigestUtils.encrypt("abc", MessageDigestEnum.SHA256));
        System.out.println(DigestUtils.encrypt("abc", MessageDigestEnum.SHA384));
        System.out.println(DigestUtils.encrypt("abc", MessageDigestEnum.SHA512));
    }
}