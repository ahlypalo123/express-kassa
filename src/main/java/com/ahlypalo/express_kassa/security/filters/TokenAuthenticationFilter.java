package com.ahlypalo.express_kassa.security.filters;

import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.repository.MerchantRepository;
import com.ahlypalo.express_kassa.security.token.TokenAuthentication;
import com.ahlypalo.express_kassa.service.AuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

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

    if (token == null) {
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
