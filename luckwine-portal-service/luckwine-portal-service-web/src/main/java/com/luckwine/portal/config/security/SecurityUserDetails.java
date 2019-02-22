package com.luckwine.portal.config.security;

import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.portal.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author HowellYang
 */
@Slf4j
public class SecurityUserDetails extends CustInfo implements UserDetails {

    private static final long serialVersionUID = 1L;

    CustInfo user = new CustInfo();

    public SecurityUserDetails(CustInfo user) {
        if(user!=null) {
            this.user = user;
            this.setStatus(user.getStatus());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorityList = new ArrayList<>();
        // todo
        return authorityList;
    }

    @Override
    public String getPassword() {
        return  this.user.getLoginPw();
    }

    @Override
    public String getUsername() {
        return this.user.getLoginAccount();
    }

    /**
     * 账户是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    /**
     * 是否禁用 "客户状态：1.正常 2.冻结", 状态为1:才能正常登陆
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {

        return CommonConstant.USER_STATUS_LOCK.equals(this.getStatus()) ? false : true;
    }

    /**
     * 密码是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    /**
     * 是否启用 "客户状态：1.正常 2.冻结", 状态为1:才能正常登陆
     * @return
     */
    @Override
    public boolean isEnabled() {

        return CommonConstant.USER_STATUS_NORMAL.equals(this.getStatus()) ? true : false;
    }
}