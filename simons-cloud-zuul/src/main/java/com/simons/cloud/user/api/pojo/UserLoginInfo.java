package com.simons.cloud.user.api.pojo;

import com.simons.cloud.user.api.enums.LoginTypeEnum;
import com.simons.cloud.user.api.model.SysUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginInfo extends SysUser {
	private static final long serialVersionUID = 1L;

	/**
	 * 记录登录方式
	 */
	private LoginTypeEnum loginTypeEnum;
	/**
	 * 用户的身份id
	 */
	private String roleId;
	/**
	 * 用户的身份号
	 */
	private String roleCode;
}
