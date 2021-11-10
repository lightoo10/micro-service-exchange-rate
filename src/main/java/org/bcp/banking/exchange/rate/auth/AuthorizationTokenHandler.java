package org.bcp.banking.exchange.rate.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.bcp.banking.exchange.rate.config.Secured;
import org.bcp.banking.exchange.rate.exceptions.JwtNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AuthorizationTokenHandler extends HandlerInterceptorAdapter {

    private static final String PREFIX_BEARER = "Bearer ";
    private static final String MISSING_HEADER = "MISSING_HEADER";
    private static final String SUB = "sub";
    private static final String HEADER = "Authorization";

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Secured annotation = handlerMethod.getMethodAnnotation(Secured.class);
            if (annotation != null) {
                String token = Optional.ofNullable(request.getHeader(HEADER)).orElseGet(() -> MISSING_HEADER);
                String authToken = token.replace(PREFIX_BEARER, "");
                if (token.isEmpty()) {
                    throw new JwtNotFoundException("Authorization Token is required");
                } else {
                    try {
                        if (token.contains(PREFIX_BEARER)) {
                            DecodedJWT decode = jwtTokenUtil.getDecodedJWT(authToken);
                            if (jwtTokenUtil.validateToken(decode)) {
                                String username = jwtTokenUtil.getClaim(decode, "username").asString();
                                List<String> auth = jwtTokenUtil.getClaim(decode, "auth").asList(String.class);
                                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, auth.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())));
                            } else {
                                throw new JwtNotFoundException("Authorization Token is required");
                            }
                        } else {
                            throw new JwtNotFoundException("Authorization Token is required");
                        }
                    } catch (Exception e) {
                        throw new JwtNotFoundException("Authorization Token is required");
                    }

                }
            }
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}