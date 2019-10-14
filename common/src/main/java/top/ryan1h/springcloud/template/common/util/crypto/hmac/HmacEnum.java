package top.ryan1h.springcloud.template.common.util.crypto.hmac;

/**
 * Hmac算法枚举
 */
public enum HmacEnum {
    HmacMD5("HmacMD5"),
    HmacSHA1("HmacSHA1"),
    HmacSHA256("HmacSHA256"),
    HmacSHA384("HmacSHA384"),
    HmacSHA512("HmacSHA512"),
    ;

    private String name;

    HmacEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
