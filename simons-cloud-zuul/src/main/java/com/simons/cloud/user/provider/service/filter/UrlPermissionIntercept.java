package com.simons.cloud.user.provider.service.filter;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.simons.cloud.user.api.config.configure.SecurityConfigure;
import com.simons.cloud.user.api.config.configure.WhileListConfigure;
import com.simons.cloud.user.api.pojo.UserLoginInfo;
import com.simons.cloud.user.api.service.RolePermissionService;
import com.simons.common.exception.CommonErrorType;
import com.simons.common.exception.CommonException;

/**
 * 权限拦截器
 * 
 * @Description:TODO
 * 
 * @author:jsm
 * 
 * @time:2017年9月28日 下午3:55:57
 */
@Component
public class UrlPermissionIntercept {

	@Resource
	private RolePermissionService sysRolePermissionsService;

	@Resource
	private WhileListConfigure whileListConfigure;

	@Resource
	private SecurityConfigure securityConfigure;

	private static final String OPENAUTHUSER = "OPEN"; // 开启默认调试用户的标签

	private static final String OPTIONSMETHOD = "OPTIONS"; // AJAX访问跨域链接是否可行的方法

	/**
	 * 用户登录信息校验 接口权限校验拦截
	 * 
	 */
	public boolean preHandle(HttpServletRequest request) throws Exception {
		String URI = request.getRequestURI();

		boolean flag = true;
		// 获取并校验请求方法
		String method = request.getMethod();
		if (!OPTIONSMETHOD.equals(method)) {
			// 如果是默认用户模式，放行
			String UserAuth = request.getHeader("UserAuth");
			if (!judgeOpenAuth(UserAuth)) {
				// 如果uri在白名单内，放行
				if (!judgeWhileList(URI)) {

					String token = request.getHeader("token");

					// token字符串校验
					if (StringUtils.isBlank(token)) {
						String getToken = request.getParameter("token");
						token = getToken;
						if (StringUtils.isBlank(token)) {
							throw new CommonException(CommonErrorType.COMMON_DATA_NULL, ":token为空，该操作为非法操作");
						}
					}
					// 登录验证
					UserLoginInfo UserLoginInfo = sysRolePermissionsService.getUserInfoByToken(token);

					// 接口权限校验
					flag = sysRolePermissionsService.judgeRolePermission(URI, UserLoginInfo.getRoleId());
				}
			}
		}
		return flag;
	}

	/**
	 * 判断uri是否在白名单内
	 */
	private boolean judgeWhileList(String nowUri) {
		boolean flag = false;
		if (whileListConfigure != null) {
			List<String> whileList = whileListConfigure.getWhite();
			if (whileList != null && whileList.size() != 0) {
				for (String uri : whileList) {
					if (nowUri.contains(uri)) {
						flag = true;
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 检查当前是否是开启了默认用户模式
	 * 
	 * @return
	 */
	private boolean judgeOpenAuth(String userAuth) {
		boolean flag = false;
		if (StringUtils.isBlank(userAuth)) {
			return flag;
		}
		if (securityConfigure != null) {
			if (OPENAUTHUSER.equals(securityConfigure.getIsOpenAuthUser())
					&& securityConfigure.getIsOpenAuthUser().equals(userAuth)) {
				flag = true;
			}
		}
		return flag;
	}
}
