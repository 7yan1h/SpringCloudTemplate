package top.ryan1h.springcloud.template.common.mybatis.dto;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@Deprecated
public class QueryParam<T> implements Serializable {
    private static final long serialVersionUID = 6379521363261290277L;

    /**
     * 小于1时通过setter方法设置默认值
     */
    private int current = 1;

    /**
     * 小于1时通过setter方法设置默认值
     */
    private int size = 10;

    @Valid
    private T condition;

    @Valid
    private List<Sort> sorts;

    public QueryParam() {
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current < 1 ? 1 : current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size < 1 ? 10 : size;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }

    public List<Sort> getSorts() {
        return sorts;
    }

    public void setSorts(List<Sort> sorts) {
        this.sorts = sorts;
    }

}
