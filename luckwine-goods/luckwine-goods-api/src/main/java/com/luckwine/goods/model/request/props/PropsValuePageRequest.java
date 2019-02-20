package com.luckwine.goods.model.request.props;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class PropsValuePageRequest extends BaseRequest {

    private Long keyId;

    private String value;

}
