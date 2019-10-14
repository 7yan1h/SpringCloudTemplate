package top.ryan1h.springcloud.template.common.object;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseBean implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 创建人id
     */
    private Long createId;
    /**
     * 创建人姓名
     */
    private String createName;
    /**
     * 创建时间
     */
    private Date createDt;
    /**
     * 更新人id
     */
    private Long updateId;
    /**
     * 更新人姓名
     */
    private String updateName;
    /**
     * 更新时间
     */
    private Date updateDt;

}
