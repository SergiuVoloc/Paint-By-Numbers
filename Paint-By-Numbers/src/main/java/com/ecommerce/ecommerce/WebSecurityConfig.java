package com.ecommerce.ecommerce;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(new BCryptPasswordEncoder())
			.usersByUsernameQuery("select username, password, enabled from user_app where username=?")
			.authoritiesByUsernameQuery("select username, role from user_app where username=?")
			;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//				.anyRequest().permitAll()
				.antMatchers("/",
				"/slider/**",
				"/product/**",
				"/category/**",
				"/css/**",
				"/js/**",
				"/assets/**",
				"/file/**",
				"/fonts/**",
				"/global/**",
				"/images/**",
				"/auth/**").permitAll()
			.antMatchers("/edit/*", "/delete/*").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin().permitAll()
			.and()
			.logout().permitAll()
			.and()
			.csrf().disable()
			.exceptionHandling().accessDeniedPage("/403")
			;
	}
}