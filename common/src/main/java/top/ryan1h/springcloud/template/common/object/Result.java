package top.ryan1h.springcloud.template.common.object;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ryan1h.springcloud.template.common.enums.MsgEnum;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    public static final MsgEnum DEFAULT_SUCCESS_MSG_ENUM = MsgEnum.S00000;

    public static final MsgEnum DEFAULT_ERROR_MSG_ENUM = MsgEnum.E00000;

    private static final long serialVersionUID = -2410305335190791152L;

    /**
     * 操作状态
     */
    @ApiModelProperty("操作状态")
    private boolean status = true;

    /**
     * 状态码
     */
    @ApiModelProperty("状态码")
    private String code;

    /**
     * 主提示信息
     */
    @ApiModelProperty("主提示信息")
    private String message;

    /**
     * 详细描述
     */
    @ApiModelProperty("详细描述")
    private String description;

    /**
     * 响应数据
     */
    @ApiModelProperty("响应数据")
    private T data;

    public static Result success() {
        Result result = new Result();
        result.setCode(DEFAULT_SUCCESS_MSG_ENUM.getCode());
        result.setMessage(DEFAULT_SUCCESS_MSG_ENUM.getMessage());

        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(DEFAULT_SUCCESS_MSG_ENUM.getCode());
        result.setMessage(DEFAULT_SUCCESS_MSG_ENUM.getMessage());

        return result;
    }

    public static Result success(MsgEnum msgEnum) {
        Result result = new Result<>();
        result.setCode(msgEnum.getCode());
        result.setMessage(msgEnum.getMessage());

        return result;
    }

    public static <T> Result<T> success(T data, MsgEnum msgEnum) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(msgEnum.getCode());
        result.setMessage(msgEnum.getMessage());

        return result;
    }

    public static Result error(String message) {
        Result result = new Result();
        result.setStatus(false);
        result.setCode(DEFAULT_ERROR_MSG_ENUM.getCode());
        result.setMessage(message);

        return result;
    }

    public static Result error(MsgEnum msgEnum) {
        Result result = new Result();
        result.setStatus(false);
        result.setCode(msgEnum.getCode());
        result.setMessage(msgEnum.getMessage());

        return result;
    }

    public static <T> Result error(T data, MsgEnum msgEnum) {
        Result<T> result = new Result<>();
        result.setStatus(false);
        result.setData(data);
        result.setCode(msgEnum.getCode());
        result.setMessage(msgEnum.getMessage());

        return result;
    }

    public static <T> Result<T> error(T data, MsgEnum msgEnum, String description) {
        Result<T> result = new Result<>();
        result.setStatus(false);
        result.setData(data);
        result.setCode(msgEnum.getCode());
        result.setMessage(msgEnum.getMessage());
        result.setDescription(description);

        return result;
    }

    public static Result error(MsgEnum msgEnum, String description) {
        Result result = new Result();
        result.setStatus(false);
        result.setCode(msgEnum.getCode());
        result.setMessage(msgEnum.getMessage());
        result.setDescription(description);

        return result;
    }
}
