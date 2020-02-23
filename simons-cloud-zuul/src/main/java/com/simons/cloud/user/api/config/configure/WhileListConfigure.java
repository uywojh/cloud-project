package com.simons.cloud.user.api.config.configure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "uri")
public class WhileListConfigure {
	private List<String> white = new ArrayList<String>();
}