package top.ryan1h.springcloud.template.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MsgEnum {
    /**
     * 成功操作,以S开头;失败操作,以E开头
     */
    S00000("操作成功", "S00000"),
    S00001("登录成功", "S00001"),
    S00002("退出登录成功", "S00002"),

    /**
     * E00000-E00099,系统错误
     */
    E00000("未知的错误信息", "E00000"),
    E00001("Not Found", "E00001"),
    E00002("参数验证错误", "E00002"),
    E00003("上传错误", "E00003"),
    E00004("请先登录", "E00004"),
    E00005("用户名不存在", "E00005"),
    E00006("无访问权限", "E00006"),
    E00007("账号被锁定", "E00007"),
    E00008("登录失败", "E00008"),
    E00009("身份验证已过期,请重新登录", "E00009"),
    E00010("用户名密码不匹配", "E00010"),
    E00011("缺少认证参数", "E00011"),
    E00012("token不合法", "E00012"),
    E00013("请求方法不正确", "E00013"),
    E00014("不合法的授权码", "E00014"),


    /**
     * 景点,E00100~E00199
     */
    E40201("添加景点信息失败", "E00100"),
    E40202("修改景点信息失败", "E00101"),
    E40203("删除景点失败", "E00102"),
    E40204("修改景点状态失败", "E00103"),
    ;

    private final String message;

    private final String code;

    MsgEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
