package com.cni.elearning.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.cni.elearning.Services.Security.IJWTService;
import com.cni.elearning.Services.Users.IUserService;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final IJWTService jwtService;

    private final IUserService userService;


    @Autowired
    public JwtAuthenticationFilter(@Lazy IUserService userService,IJWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                final String authHeader = request.getHeader("Authorization");
                final String jwt;
                final String userEmail;
                
                if (StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")){
                    filterChain.doFilter(request, response);
                    return;
                }
                jwt = authHeader.substring(7);
                userEmail = jwtService.extractUsername(jwt);
                if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);

                    if (jwtService.isTokenValid(jwt, userDetails)){
                       SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                       UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                        );
                        
                        token.setDetails( new WebAuthenticationDetailsSource().buildDetails(request));

                        securityContext.setAuthentication(token);
                        SecurityContextHolder.setContext(securityContext);
                    }
                }
                filterChain.doFilter(request, response);
    }

}
