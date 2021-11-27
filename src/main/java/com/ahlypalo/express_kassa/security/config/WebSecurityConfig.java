package com.ahlypalo.express_kassa.security.config;

import com.ahlypalo.express_kassa.security.filters.TokenAuthenticationFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@ComponentScan("com.ahlypalo.express_kassa")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final TokenAuthenticationFilter tokenAuthenticationFilter;

  public WebSecurityConfig(TokenAuthenticationFilter tokenAuthenticationFilter) {
    this.tokenAuthenticationFilter = tokenAuthenticationFilter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http = http.cors().and().csrf().disable();

    http = http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and();

    http
      .addFilterBefore(tokenAuthenticationFilter, BasicAuthenticationFilter.class)
      .authorizeRequests()
      .antMatchers("/auth/**", "/").permitAll()
      .anyRequest().authenticated();
  }
}
