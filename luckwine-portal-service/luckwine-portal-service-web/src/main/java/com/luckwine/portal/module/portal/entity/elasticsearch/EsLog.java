package com.luckwine.portal.module.portal.entity.elasticsearch;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luckwine.portal.common.constant.CommonConstant;
import com.luckwine.portal.common.utils.ObjectUtil;
import com.luckwine.portal.common.utils.SnowFlakeUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;


/**
 * Elasticsearch文档实体类
 * @author HowellYang
 */
@Data
@Document(indexName = "oss_log", type = "log", shards = 1, replicas = 0, refreshInterval = "-1")
public class EsLog implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty(value = "唯一标识")
    private String id = String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @Field(type = FieldType.Text, fielddata = true)
    private Date createTime = new Date();

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "删除标志 默认0")
    private Integer delFlag = CommonConstant.STATUS_NORMAL;

    @ApiModelProperty(value = "方法操作名称")
    @Field(type = FieldType.Text, index = true)
    private String name;

    @ApiModelProperty(value = "请求路径")
    @Field(type = FieldType.Text, index = true)
    private String requestUrl;

    @ApiModelProperty(value = "请求类型")
    private String requestType;

    @ApiModelProperty(value = "请求参数")
    private String requestParam;

    @ApiModelProperty(value = "请求用户")
    private String username;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "ip信息")
    private String ipInfo;

    @ApiModelProperty(value = "花费时间")
    private Integer costTime;

    /**
     * 转换请求参数为Json
     * @param paramMap
     */
    public void setMapToParams(Map<String, String[]> paramMap) {

        this.requestParam = ObjectUtil.mapToString(paramMap);
    }
}
