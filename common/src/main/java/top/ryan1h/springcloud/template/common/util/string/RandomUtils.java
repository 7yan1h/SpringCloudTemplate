package top.ryan1h.springcloud.template.common.util.string;

import java.util.Random;

public class RandomUtils {

    private static final Random random = new Random();

    /**
     * 随机生成一个字母
     *
     * @return
     */
    public static char generateLetter() {
        int randomInt = random.nextInt(51);
        if (randomInt >= 0 && randomInt <= 25) {
            return (char) (randomInt + 65);
        } else {
            return (char) (randomInt - 26 + 97);
        }
    }

    /**
     * 随机生成一个字符
     *
     * @return
     */
    public static char generateChar() {
        int randomInt = random.nextInt(61);
        if (randomInt >= 0 && randomInt <= 9) {
            return (char) (randomInt + 48);
        } else if (randomInt >= 10 && randomInt <= 35) {
            return (char) (randomInt - 10 + 65);
        } else {
            return (char) (randomInt - 36 + 97);
        }
    }

    /**
     * 随机生成指定最大长度的String
     * 至少会生成一位字符的String
     *
     * @param maxLength 生成的String的最大长度
     * @return
     */
    public static String generateMaxLengthString(int maxLength) {
        if (maxLength <= 1) {
            return String.valueOf(generateChar());
        }
        int maxRound = random.nextInt(maxLength + 1);
        if (maxRound <= 1) {
            return String.valueOf(generateChar());
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < maxRound; i++) {
            stringBuffer.append(generateChar());
        }
        return stringBuffer.toString();
    }

    /**
     * 随机生成指定长度的String
     *
     * @param length 生成的String的长度
     * @return
     */
    public static String generateLengthString(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(generateChar());
        }
        return stringBuffer.toString();
    }

}

