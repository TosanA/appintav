package fr.uha.appintav.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.uha.appintav.filter.AuthFilter;

@Configuration
public class AuthFilterConfig {
	
	@Bean
	public FilterRegistrationBean<AuthFilter> filterRegistration(){
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/colocations/*");
        registrationBean.addUrlPatterns("/tasks/*");
        registrationBean.addUrlPatterns("/users/*");
        return registrationBean;
	}
}
