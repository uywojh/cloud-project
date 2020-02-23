package com.simons.cloud.user.api.cache;

public class NameContext {
	/**
	 * 用户登录后保存于redis中的信息头
	 */
	public static final String USER_LOGIN_HEADER = "user_login_";

	/**
	 * 不同角色的权限菜单保存于redis中的信息头
	 */
	public static final String PERMISSION_INFO = "permission_info_";

	/**
	 * 用户信息cookie中存放的名字
	 */
	public static final String USER_INFO_COOKIE = "UserInfo";

	/**
	 * 返回用户信息的cookie中存放的token的名字
	 */
	public static final String TOKEN = "token";

	/**
	 * 树形结构的根节点
	 */
	public static final Long TOP_TREE_NODE = 1L;

	/**
	 * 不同角色的菜单列表保存于redis中的信息头
	 */
	public static final String MEUN_ROLE_HEADER = "meun_role_";

	/**
	 * 验证码的名字
	 */
	public static final String CHECK_CODE_NAME = "check_code_";
	
	/**
	 * 总菜单树在redis中的名称
	 */
	public static final String NODE_MENU_TREE_NAME = "node_menu_tree";
}
