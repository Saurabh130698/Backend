package robo.backend.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import robo.backend.service.JWTService;
import robo.backend.service.UserAuthService;

@Component
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    JWTService jwtService;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String username = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")) {

            jwtToken = authHeader.substring(7);
            username = jwtService.extractUsername(jwtToken);

        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

           UserDetails userDetails = applicationContext.getBean(UserAuthService.class).loadUserByUsername(username);
          
           if(jwtService.validationToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken token = 
                      new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);    
            } 
        }

        filterChain.doFilter(request, response);
    }

}
