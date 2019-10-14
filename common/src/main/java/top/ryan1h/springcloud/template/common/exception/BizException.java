package top.ryan1h.springcloud.template.common.exception;

import lombok.Data;
import top.ryan1h.springcloud.template.common.enums.MsgEnum;

/**
 * 封装业务异常
 */
@Data
public class BizException extends RuntimeException {

    private MsgEnum msgEnum;

    private String description;

    private Object data;

    public BizException(MsgEnum msgEnum) {
        super(msgEnum.getMessage());

        this.msgEnum = msgEnum;
    }

    public BizException(Object data, MsgEnum msgEnum) {
        super(msgEnum.getMessage());

        this.msgEnum = msgEnum;
        this.data = data;
    }

    public BizException(MsgEnum msgEnum, String description) {
        super(msgEnum.getMessage());

        this.msgEnum = msgEnum;
        this.description = description;
    }

    public BizException(Object data, MsgEnum msgEnum, String description) {
        super(msgEnum.getMessage());

        this.msgEnum = msgEnum;
        this.data = data;
        this.description = description;
    }
}


