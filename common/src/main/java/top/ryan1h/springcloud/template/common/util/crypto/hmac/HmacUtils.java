package top.ryan1h.springcloud.template.common.util.crypto.hmac;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacUtils {

    private static final String ENCODING = "UTF-8";

    /**
     * 生成签名
     *
     * @param data     带签名数据
     * @param key      密钥
     * @param hmacEnum 签名算法
     * @return
     */
    public static String sign(String data, String key, HmacEnum hmacEnum) {
        try {
            Mac mac = Mac.getInstance(hmacEnum.name());
            mac.init(new SecretKeySpec(key.getBytes(ENCODING), hmacEnum.getName()));

            return Base64.encodeBase64String(mac.doFinal(data.getBytes(ENCODING)));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 校验签名
     *
     * @param data     签名前的数据
     * @param sign     签名后端数据
     * @param key      密钥
     * @param hmacEnum 签名算法
     * @return
     */
    public static boolean check(String data, String sign, String key, HmacEnum hmacEnum) {
        String sign1 = sign(data, key, hmacEnum);
        if (sign.equals(sign1)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String data = "abc";
        String key = "hx";
        String sign = HmacUtils.sign(data, key, HmacEnum.HmacSHA256);
        System.out.println(sign);
        System.out.println(sign.length());

        System.out.println(HmacUtils.check(data, sign, key, HmacEnum.HmacSHA256));
    }
}
