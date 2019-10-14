package top.ryan1h.springcloud.template.common.util.crypto.rsa;

/**
 * RSA算法枚举
 */
public enum RSAEnum {

    RSA256(2048),
    RSA384(3072),
    RSA512(4096),
    ;

    private int keySize;

    RSAEnum(int keySize) {
        this.keySize = keySize;
    }

    public int getKeySize() {
        return keySize;
    }
}
