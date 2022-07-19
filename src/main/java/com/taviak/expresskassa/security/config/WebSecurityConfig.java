package com.taviak.expresskassa.security.config;

import com.taviak.expresskassa.security.filters.TokenAuthenticationFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@ComponentScan("com.taviak.expresskassa")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final TokenAuthenticationFilter tokenAuthenticationFilter;

  public WebSecurityConfig(TokenAuthenticationFilter tokenAuthenticationFilter) {
    this.tokenAuthenticationFilter = tokenAuthenticationFilter;
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
      .antMatchers("/resources/public");
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
      .antMatchers(
              "/auth/**", "/",
              "/user-photos/**",
              "/actuator/**",
              "/merchant/update-password",
              "/swagger-resources/**",
              "/swagger-ui/**",
              "/swagger-ui.html",
              "/v2/api-docs",
              "/webjars/**"
      ).permitAll()
      .anyRequest().authenticated();
  }
}
