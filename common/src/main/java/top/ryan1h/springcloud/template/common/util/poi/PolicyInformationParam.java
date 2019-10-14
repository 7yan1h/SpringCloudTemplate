package top.ryan1h.springcloud.template.common.util.poi;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 政策文件新增/修改请求参数
 *
 * @author zhangkaichuan
 * @date 2019/08/05
 */
@Data
public class PolicyInformationParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 索引号
     */
    @ColumnIndex(1)
    private String reference;

    /**
     * 信息分类
     */
    private String infotype;

    /**
     * 政策层级
     */
    @ColumnIndex(2)
    private String level;

    /**
     * 效力状态  1 为有效 、 2 为无效
     */
    @ColumnIndex(13)
    private Integer status;

    /**
     * 文号
     */
    @ColumnIndex(4)
    private String documentSymbol;

    /**
     * 发文日期
     */
    @ColumnIndex(10)
    private String publicDate;

    /**
     * 年号
     */
    @ColumnIndex(5)
    private String yearNo;

    /**
     * 有效期至
     */
    @ColumnIndex(12)
    private String dateExpiry;

    /**
     * 期号
     */
    @ColumnIndex(6)
    private String issue;

    /**
     * 生成日期
     */
    private String createTime;

    /**
     * 政策标题
     */
    @ColumnIndex(3)
    private String title;

    /**
     * 政策摘要
     */
    @ColumnIndex(8)
    private String summary;

    /**
     * 行业门类
     */
    @ColumnIndex(15)
    private String[] matchIndustryArray;

    /**
     * 适用区域
     */
    @ColumnIndex(16)
    private String[] matchAreaArray;

    /**
     * 适用对象
     */
    @ColumnIndex(17)
    private String[] matchPeopleArray;

    /**
     * 政策类别
     */
    @ColumnIndex(18)
    private String[] policyInfoTypeArray;

    /**
     * 相关政策
     */
    private String[] policyRelevantArray;

    /**
     * 政策文件内容(html格式)
     */
    private String contentPolicyHtml;

    /**
     * 政策内容url
     */
    private String contentPolicyUrl;

    public void setPublicDate(Date publicDate) {
        if (publicDate != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            this.publicDate = simpleDateFormat.format(publicDate);
        }
    }

    public void setDateExpiry(Date dateExpiry) {
        if (dateExpiry != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            this.dateExpiry = simpleDateFormat.format(dateExpiry);
        }
    }

    public void setStatus(String status) {
        if (StringUtils.isNotEmpty(status)) {
            this.status = status.equals("有效") ? 1 : 2;
        }
    }

    public void setMatchIndustryArray(String matchIndustryArray) {
        String[] localMatchIndustryArray = new String[1];

        if (StringUtils.isNotEmpty(matchIndustryArray)) {
            localMatchIndustryArray[0] = matchIndustryArray;
            this.matchIndustryArray = localMatchIndustryArray;
        }
    }

    public void setMatchAreaArray(String matchAreaArray) {
        String[] localMatchAreaArray = new String[1];

        if (StringUtils.isNotEmpty(matchAreaArray)) {
            localMatchAreaArray[0] = matchAreaArray;
            this.matchAreaArray = localMatchAreaArray;
        }
    }

    public void setMatchPeopleArray(String matchPeopleArray) {
        String[] localMatchPeopleArray = new String[1];

        if (StringUtils.isNotEmpty(matchPeopleArray)) {
            localMatchPeopleArray[0] = matchPeopleArray;
            this.matchPeopleArray = localMatchPeopleArray;
        }
    }

    public void setPolicyInfoTypeArray(String policyInfoTypeArray) {
        String[] localPolicyInfoTypeArray = new String[1];

        if (StringUtils.isNotEmpty(policyInfoTypeArray)) {
            localPolicyInfoTypeArray[0] = policyInfoTypeArray;
            this.policyInfoTypeArray = localPolicyInfoTypeArray;
        }
    }

}