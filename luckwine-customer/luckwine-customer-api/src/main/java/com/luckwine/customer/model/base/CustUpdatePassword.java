package com.luckwine.customer.model.base;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;

@Data
@ToString
public class CustUpdatePassword extends BaseRequest {

	@Id
	private String loginAccount;

	private String currentPassword;

	private String newPassword;

	private String configPassword;

}
