package com.simons.cloud.user.provider.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.simons.cloud.user.api.cache.NameContext;
import com.simons.cloud.user.api.cache.TypeTokenCache;
import com.simons.cloud.user.api.config.configure.UserConfigure;
import com.simons.cloud.user.api.pojo.UserLoginInfo;
import com.simons.cloud.user.api.service.RolePermissionService;
import com.simons.common.exception.CommonErrorType;
import com.simons.common.exception.CommonException;
import com.simons.common.utils.json.GsonUtil;
import com.simons.module.cache.redis.RedisService;

@Service
public class RolePermissionServiceImpl implements RolePermissionService{
	
	@Resource
	private UserConfigure userConfigure;
	
	@Resource
	private RedisService<String, Object> redisService;

	@Override
	public boolean judgeRolePermission(String URI, String roleId) throws Exception {
		String fixingURI = specificationURI(URI);
		// 根据uri查询redis中的角色列表
		Object permissionJsonObj = redisService.get(NameContext.PERMISSION_INFO + fixingURI);
		List<String> permissionList = null;
		if (permissionJsonObj != null) {
			String permissionJson = String.valueOf(permissionJsonObj);
			permissionList = GsonUtil.GSON.fromJson(permissionJson, TypeTokenCache.LISTTOKEN);
		}
		// 当查出的permissionList不为空时，遍历当中的roleId，匹配用户自己的roleId，有就放行，没有就抛出异常
		if (permissionList != null && permissionList.size() != 0) {
			for (String permission : permissionList) {
				if (StringUtils.isNoneBlank(permission) && permission.equals(roleId)) {
					return true;
				}
			}
		}
		throw new CommonException(CommonErrorType.COMMON_ERROR, ":用户没有该接口权限");
	}

	@Override
	public UserLoginInfo getUserInfoByToken(String token) throws Exception {
		Object obj = redisService.get(NameContext.USER_LOGIN_HEADER + token);
		// 当获取到的信息不为空时，
		if (obj != null) {
			String userInfo = String.valueOf(obj);
			if (StringUtils.isNotBlank(userInfo)) {
				UserLoginInfo userLoginInfo = GsonUtil.GSON.fromJson(userInfo, UserLoginInfo.class);
				if (userLoginInfo != null) {
					redisService.set(NameContext.USER_LOGIN_HEADER, userInfo, userConfigure.getUserExpireTime());
					return userLoginInfo;
				}

			}
		}
		throw new CommonException(CommonErrorType.LOGION_FAIL, ":用户登录已超时，请重新登录");
	}

	// uri规范化
	private String specificationURI(String URI) {
		String[] fixingURI = URI.split("/api");
		return fixingURI[1];
	}
}
