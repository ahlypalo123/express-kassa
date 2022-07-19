package com.taviak.expresskassa.security.filters;

import com.taviak.expresskassa.entity.Merchant;
import com.taviak.expresskassa.repository.MerchantRepository;
import com.taviak.expresskassa.security.token.TokenAuthentication;
import com.taviak.expresskassa.service.AuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Component
public class TokenAuthenticationFilter implements Filter {

  private final AuthenticationService authenticationService;
  private final MerchantRepository merchantRepository;

  public TokenAuthenticationFilter(AuthenticationService authenticationService, MerchantRepository merchantRepository) {
    this.authenticationService = authenticationService;
    this.merchantRepository = merchantRepository;
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    String token = ((HttpServletRequest) request).getHeader(HttpHeaders.AUTHORIZATION);

    if (!StringUtils.hasText(token)) {
      chain.doFilter(request, response);
      return;
    }

    String email = authenticationService.parseToken(token).getSubject();
    Optional<Merchant> m = merchantRepository.findOneByEmail(email);

    if (m.isPresent()) {
      TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
      tokenAuthentication.setUserDetails(m.get());
      tokenAuthentication.setAuthenticated(true);
      SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
    }
    chain.doFilter(request, response);
  }



  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
