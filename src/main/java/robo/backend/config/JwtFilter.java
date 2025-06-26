package robo.backend.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
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
            try {
                username = jwtService.extractUsername(jwtToken);
            }catch (Exception e){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"statusCode\": \"401\", \"status\": \"Unauthorized\",  \"error\": \"Invalid JWT Token\"}");
                return;
            }
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
//        else {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            respons
//            e.setContentType("application/json");
//            response.getWriter().write("{\"statusCode\": \"401\", \"status\": \"Unauthorized\", \"error\": \"Unauthorized Access\"}");
//            return;
//        }

        filterChain.doFilter(request, response);
    }
}
