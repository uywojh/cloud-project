package com.simons.cloud.user.api.service;

import com.simons.cloud.user.api.pojo.UserLoginInfo;

public interface RolePermissionService {
	public boolean judgeRolePermission(String URI, String roleId) throws Exception;

	public UserLoginInfo getUserInfoByToken(String token) throws Exception;
}
