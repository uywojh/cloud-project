package com.simons.cloud.user.api.config.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "simons.user")
public class UserConfigure {
	/**
	 * 用户信息的过期时间
	 */
	private Long userExpireTime;
}
