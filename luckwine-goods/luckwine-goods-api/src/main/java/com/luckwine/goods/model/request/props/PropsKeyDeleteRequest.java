package com.luckwine.goods.model.request.props;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
public class PropsKeyDeleteRequest extends BaseRequest {

    @NotNull
    private Long id;

}
