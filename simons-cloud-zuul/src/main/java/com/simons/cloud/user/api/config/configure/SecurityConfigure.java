package com.simons.cloud.user.api.config.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security.config")
public class SecurityConfigure {
	// 加密算法名字
	private String hashAlgorithmName;
	// 盐
	private String salt;
	// 加密次数
	private int hashIterations;
	// session的超时时间
	private Long expireTime;
	// 是否开启默认用户调试模式
	private String isOpenAuthUser;
}
