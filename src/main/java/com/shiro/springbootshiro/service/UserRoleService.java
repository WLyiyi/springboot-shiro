package com.shiro.springbootshiro.service;

import com.shiro.springbootshiro.bean.UserRole;

/**
 * Created by yangqj on 2017/4/26.
 */
public interface UserRoleService extends IService<UserRole> {

    public void addUserRole(UserRole userRole);
}
