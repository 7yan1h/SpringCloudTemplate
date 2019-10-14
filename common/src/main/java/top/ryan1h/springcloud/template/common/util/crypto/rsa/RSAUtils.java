package top.ryan1h.springcloud.template.common.util.crypto.rsa;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {
    private static final String RSA_NAME = "RSA";

    private static final String SIGNATURE_NAME = "SHA256withRSA";

    private static final String ENCODING = "UTF-8";

    /**
     * 生成非对称公钥和私钥
     *
     * @param rsaEnum
     * @return
     */
    public static KeyPair getKeyPair(RSAEnum rsaEnum) {
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(RSA_NAME);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        keyPairGenerator.initialize(rsaEnum.getKeySize());

        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 根据PublicKey得到base64编码的公钥
     *
     * @param publicKey
     * @return
     */
    public static String base64PublicKey(PublicKey publicKey) {
        return Base64.encodeBase64String(publicKey.getEncoded());
    }

    /**
     * base64编码的公钥转换为PublicKey
     *
     * @param base64PublicKey
     * @return
     */
    public static PublicKey publicKey(String base64PublicKey) {
        try {
            byte[] keyBytes = Base64.decodeBase64(base64PublicKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_NAME);

            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据PrivateKey得到base64编码的私钥
     *
     * @param privateKey
     * @return
     */
    public static String base64PrivateKey(PrivateKey privateKey) {
        return Base64.encodeBase64String(privateKey.getEncoded());
    }

    /**
     * base64编码的私钥转换为PrivateKey
     *
     * @param base64PrivateKey
     * @return
     */
    public static PrivateKey privateKey(String base64PrivateKey) {
        try {
            byte[] keyBytes = Base64.decodeBase64(base64PrivateKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_NAME);

            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 使用RSA算法对字符串加密
     *
     * @param data            加密字符串
     * @param base64PublicKey base64编码公钥
     */
    public static String encrypt(String data, String base64PublicKey) {
        try {
            byte[] decoded = Base64.decodeBase64(base64PublicKey);
            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(RSA_NAME).generatePublic(new X509EncodedKeySpec(decoded));
            Cipher cipher = Cipher.getInstance(RSA_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);

            return Base64.encodeBase64String(cipher.doFinal(data.getBytes(ENCODING)));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 使用RSA算法对字符串解密
     *
     * @param encrypt          加密字符串
     * @param base64PrivateKey base64编码私钥
     */
    public static String decrypt(String encrypt, String base64PrivateKey) {
        try {
            byte[] inputByte = Base64.decodeBase64(encrypt);
            byte[] decoded = Base64.decodeBase64(base64PrivateKey);
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(RSA_NAME).generatePrivate(new PKCS8EncodedKeySpec(decoded));
            Cipher cipher = Cipher.getInstance(RSA_NAME);
            cipher.init(Cipher.DECRYPT_MODE, priKey);

            return new String(cipher.doFinal(inputByte));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 使用RSA算法对字符串签名
     *
     * @param data             待签名字符串
     * @param base64PrivateKey base64编码私钥
     * @return
     */
    public static String sign(String data, String base64PrivateKey) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(base64PrivateKey.getBytes(ENCODING)));
            KeyFactory keyf = KeyFactory.getInstance(RSA_NAME);
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            Signature signature = Signature.getInstance(SIGNATURE_NAME);

            signature.initSign(priKey);
            signature.update(data.getBytes(ENCODING));

            byte[] signed = signature.sign();

            return new String(Base64.encodeBase64(signed));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 校验使用RSA算法签名的字符串是否合法
     *
     * @param data            签名前的字符串
     * @param sign            签名后的字符串
     * @param base64PublicKey base64编码公钥
     * @return
     */
    public static boolean check(String data, String sign, String base64PublicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_NAME);
            byte[] encodedKey = Base64.decodeBase64(base64PublicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            Signature signature = Signature.getInstance(SIGNATURE_NAME);
            signature.initVerify(pubKey);
            signature.update(data.getBytes(ENCODING));

            return signature.verify(Base64.decodeBase64(sign));
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String data = "abc154卡简历库进口量";
        KeyPair keyPair = RSAUtils.getKeyPair(RSAEnum.RSA256);
        String base64PrivateKey = RSAUtils.base64PrivateKey(keyPair.getPrivate());
        String base64PublicKey = RSAUtils.base64PublicKey(keyPair.getPublic());
        System.out.println("私钥:" + base64PrivateKey);
        System.out.println("公钥:" + base64PublicKey);

        String sign = RSAUtils.sign(data, base64PrivateKey);
        System.out.println("签名字符串:" + sign);

        System.out.println("验证签名:" + RSAUtils.check(data, sign, base64PublicKey));

        String encrypt = RSAUtils.encrypt(data, base64PublicKey);
        System.out.println("加密后:" + encrypt);

        String decrypt = RSAUtils.decrypt(encrypt, base64PrivateKey);
        System.out.println("解密后:" + decrypt);
    }
}
