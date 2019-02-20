package com.luckwine.goods.model.request.props;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
public class PropsValueModifyRequest extends BaseRequest {

    @NotNull
    private Long Id;

    @NotNull
    private Long keyId;

    @NotBlank
    private String value;

}
