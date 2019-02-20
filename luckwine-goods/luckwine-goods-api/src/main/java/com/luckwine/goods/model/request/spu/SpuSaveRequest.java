package com.luckwine.goods.model.request.spu;

import com.luckwine.goods.model.enums.SpuStatus;
import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@ToString(callSuper = true)
public class SpuSaveRequest extends BaseRequest {

	// 主键
	private String id;

    @NotBlank
    @Length(max = 128)
    private String goodsName;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long brandId;

    @Length(max = 1024)
    private String title;

    @Length(max = 1024)
    private String props;

	@Length(max = 255)
	private String propsStr;

    @NotEmpty
    @Valid
    private List<SkuVO> skus;

    @Length(max = 1024)
    private String picPath;

    @Length(max = 2048)
    private String detail;

    @NotNull
    private SpuStatus spuStatus;

	// 创建时间
	private Date createTime;
	// 创建时间字符
	private String createTimeStr;
	// 创建时间开始
	private String createTimeBeginStr;
	// 创建时间结束
	private String createTimeEndStr;
	// 更新时间
	private Date updateTime;
	// 更新时间字符
	private String updateTimeStr;
	// 更新时间开始
	private String updateTimeBeginStr;
	// 更新时间结束
	private String updateTimeEndStr;

}
