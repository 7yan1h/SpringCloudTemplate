package top.ryan1h.springcloud.template.common.mybatis.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询与排序
 *
 * @author 59941
 * @date 2019/7/8 13:08
 */
@Data
public class PageAndSort implements Serializable {
    private static final long serialVersionUID = -2189236785606974678L;

    /**
     * 小于1时通过setter方法设置默认值
     */
    @ApiModelProperty(value = "当前页", example = "1")
    private int current = 1;

    /**
     * 小于1时通过setter方法设置默认值
     */
    @ApiModelProperty(value = "当前页条数", example = "10")
    private int size = 10;

    /**
     * 排序字段,如http://localhost/students?sort=name-asc&sort=age-desc
     */
    @ApiModelProperty(value = "排序字段", notes = "如http://localhost/students?sort=name-asc&sort=age-desc")
    private String[] sort;

    public void setCurrent(int current) {
        this.current = current < 1 ? 1 : current;
    }

    public void setSize(int size) {
        this.size = size < 1 ? 10 : size;
    }
}
