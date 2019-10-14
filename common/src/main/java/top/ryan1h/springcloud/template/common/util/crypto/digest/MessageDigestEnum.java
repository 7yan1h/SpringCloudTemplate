package top.ryan1h.springcloud.template.common.util.crypto.digest;

/**
 * 摘要算法枚举
 */
public enum MessageDigestEnum {
    MD5("MD5"),
    SHA1("SHA-1"),
    SHA256("SHA-256"),
    SHA384("SHA-384"),
    SHA512("SHA-512"),
    ;

    private String name;

    MessageDigestEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
