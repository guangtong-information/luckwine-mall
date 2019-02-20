package com.luckwine.goods.model.request.props;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString(callSuper = true)
public class PropsKeyPageRequest extends BaseRequest {

    @NotBlank
    private String key;

}
