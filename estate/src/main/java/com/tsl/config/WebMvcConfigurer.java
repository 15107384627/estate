package com.tsl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.tsl.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 * 拦截器按照顺序执行
		 */
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/index/**")
													 .addPathPatterns("/cost/**")
													 .addPathPatterns("/building/**")
													 .addPathPatterns("/message/**")
													 .addPathPatterns("/owner/**")
													 .addPathPatterns("/parking/**")
													 .addPathPatterns("/user/**");
		
		super.addInterceptors(registry);
	}

}
