package top.ryan1h.springcloud.template.common.util.string;

import java.lang.reflect.Field;

public class StringUtils {
    /**
     * 将一个字符串中的大写字母替换为：_小写字母
     * 如：optionName转换为: option_name
     *
     * @param srcString 被转换的字符串
     * @return 转换后的字符串
     */
    public static String replaceToUnderlineAndLowerCase(String srcString) {
        String lowerString = srcString.toLowerCase();
        if (!srcString.equals(lowerString)) {
            StringBuilder targetString = new StringBuilder();
            char[] srcChars = srcString.toCharArray();
            for (int i = 0; i < srcChars.length; i++) {
                if (srcChars[i] >= 'A' && srcChars[i] <= 'Z') {
                    targetString.append("_");
                    targetString.append(lowerString.toCharArray()[i]);
                } else {
                    targetString.append(srcChars[i]);
                }
            }
            return targetString.toString();
        } else {
            return srcString;
        }
    }

    /**
     * 得到字符串中的扩展名<br/>
     * 如果传入的字符串没有扩展名或为空,则返回空串
     *
     * @param parseStr 被解析的字符串
     * @return
     */
    public static String getExtensionName(String parseStr) {
        if (parseStr == null || parseStr.length() == 0) {
            return "";
        }

        int dotIndex = parseStr.lastIndexOf('.');
        return (dotIndex == -1) ? "" : parseStr.substring(dotIndex + 1);
    }

    /**
     * <p>功能:      将一个dto对象中不为null的属性拼接成查询字段。
     * 例如:      GoodsParent gp = new GoodsParent;
     * gp.setId(1);
     * gp.setTitle("");
     * gp.setImgSrc("");
     * System.out.print(DbUtils.generateQueryColumns(gp));// id,title,img_src
     * 注意事项:  1.Dto类中的属性除serialVersionUID属性外不能存在默认值
     * 2.Dto类中的基本类型属性要使用对应的包装类</p>
     *
     * @param targetDto 需要生成查询字符串的dto对象,只需要给这个dto对象中需要转换的属性设置任何一个值即可,不需要转换的属性不要设置值。
     * @return 转换后的查询字符串
     */
    public static String generateQueryColumns(Object targetDto) {
        Field[] fields = targetDto.getClass().getDeclaredFields();
        StringBuilder queryColumns = new StringBuilder();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if (!field.getName().equals("serialVersionUID")) {
                    if (field.get(targetDto) != null) {
                        queryColumns.append(StringUtils.replaceToUnderlineAndLowerCase(field.getName())).append(",");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        queryColumns.deleteCharAt(queryColumns.length() - 1);

        return queryColumns.toString();
    }
}
