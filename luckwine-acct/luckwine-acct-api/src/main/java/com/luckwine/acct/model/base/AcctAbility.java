package com.luckwine.acct.model.base;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Table;
import java.util.Date;

@Data
@ToString(callSuper = true)
@Table(name = "acct_ability")
public class AcctAbility {
    /**
     * 主键
     */
    /*@Id
    private String id;*/

    /**
     * 账户号
     */
    private String acctCode;

    /**
     * 账户能力
     */
    private String abilityCode;

    /**
     * 开户时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}