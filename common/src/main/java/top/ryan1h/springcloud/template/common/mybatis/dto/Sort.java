package top.ryan1h.springcloud.template.common.mybatis.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author 59941
 * @date 2019/5/7 20:15
 */
@Deprecated
public class Sort implements Serializable {

    private static final long serialVersionUID = 3960460571098162735L;

    /**
     * 排序列名
     */
    @NotBlank
    private String sortColumn;

    /**
     * 排序类型
     */
    @Pattern(regexp = "(ASC|DESC)")
    private String sortType;

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

}
