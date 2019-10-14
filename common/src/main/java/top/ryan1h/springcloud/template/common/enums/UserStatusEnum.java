package top.ryan1h.springcloud.template.common.enums;

/**
 * 用户账号枚举
 */
public enum UserStatusEnum {

    /**
     * 启用
     */
    ENABLE(0),

    /**
     * 禁用
     */
    BAN(1);

    private Integer status;

    UserStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
